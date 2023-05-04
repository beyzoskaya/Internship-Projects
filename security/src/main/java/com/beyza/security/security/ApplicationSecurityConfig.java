package com.beyza.security.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import java.util.concurrent.TimeUnit;

import static com.beyza.security.security.ApplicationUserRole.*;

/*
configuring everything about security
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    /*
    when click kntrl + o ----> get the override methods
    starting config adaption we choose HttpSecurity configuration
     */

    private final PasswordEncoder passwordEncoder;


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }



    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

/*
when the client (frontend) login to the server
spring security sends csrf token to the client
then frontend uses this token for any form submission(post/put/delete)
server validates form submissions
 */

/*
without .csrf().disable() burak cannot post the new student because server could not validate the forms
after adding csrf burak can post a new student
 */


    /*
    BASIC AUTH
    ----------
    client want to access some of the requests
    client sends a request ----> get 401 status meaning AUTHORIZED
    after client send a request ---> password endcoding must be done (in base64 format)
    if the password correct -----> status 200

    and().httpBasic()
    in basic auth we get the auth token every single time

    saying that we are going to do basicAuth we need to choose Basic Autharization in Postman in Headers
    then gave the password from the console
     */

    /*
    FORM BASED AUTH
    ---------------
    client send user and password request then server validates credentials then if username and password
    run in with ----> COOKIE SESSIONID validated (unique)
    server responded OK (status 200)
    SESSIONID db'de tutuluyor  ----> in memory database meaning that when we refresh the localhost:8081
    we get the another value for application-cookies-value
    and().formLogin()
     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                //.and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/index", "/css/*", "/js/*")
                .permitAll()
                .antMatchers("/api/**").hasRole(STUDENT.name())
                //.antMatchers(HttpMethod.DELETE,"/management/api/**").hasRole(ApplicationUserPermisson.COURSE_WRITE.getPermission())
                //.antMatchers(HttpMethod.POST,"/management/api/**").hasRole(ApplicationUserPermisson.COURSE_WRITE.getPermission())
                //.antMatchers(HttpMethod.PUT,"/management/api/**").hasRole(ApplicationUserPermisson.COURSE_WRITE.getPermission())
                //.antMatchers(HttpMethod.GET,"/management/api/**").hasAnyRole(ApplicationUserRole.ADMIN.name(),ApplicationUserRole.ADMINTRAINEE.name())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/courses",true)
                .and()  // and as a way to chain methods ---> some configurer
                                                          // .<some feature of configurer>
                                                          // and().
                /*.rememberMe()
                .tokenValiditySeconds((int)TimeUnit.DAYS.toSeconds(21))
                // defaults to 2 weeks
                               // remember me is also cookie (stored in the database)
                               // if we restart the project ----> loose the cookie
                .and()*/
                .logout()
                  .logoutUrl("/logout")
                  .clearAuthentication(true)
                  .invalidateHttpSession(true)
                  .logoutSuccessUrl("/login");
    }

    /*
    when we added the form based auth
    localhost gave us login page and then burak can
    access the students but beyzoskaya go to the direct home page
    but we do not formulize it to the one by one getting
    beyzoskaya is still can get students via id one by one
    (only geAllStudents() methods did not work)
     */


    /*
    when we are trying to configure http request
    we are trying the use basic authorization  so
    due to that when we are trying to send any request from postman
    we also add authorization type
    in basic auth page ----> postman asked for username and password
     */

    /*
    after gave the username and password
    we got the student with id that we are given
    so in this case user is an admin
     */


    /*
    before given second antMatcher pattern both beyzoskaya and burak can access the student with id
    but after giving a role in the antMatcher only beyzoskaya can access the student with id
    burak cannot acces -----> {
    "timestamp": "2022-09-15T06:27:32.524+00:00",
    "status": 403,
    "error": "Forbidden",
    "path": "/api/v1/students/3"
}

     beyzoskaya can access -----> {
    "studentId": 3,
    "studentName": "Anna Smith"
}
     beyzoskaya is a STUDENT so she can access -----> api/v1/students
     role based auth
     */

    /*
    user from the db
     */


    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails beyzoskayaUser = User
                .withDefaultPasswordEncoder()
                .username("beyzoskaya")
                .password(passwordEncoder.encode("{noop}password"))
                .authorities(STUDENT.getGrantedAuthorities())
                //.roles(STUDENT.name())// ROLE STUDENT!!!!
                .build();

        /* instead of giving STUDENT role in the config level
           we are trying to generalize it then we can use easiy
           STUDENT goes to ------> ApplicationUserRole.STUDENT.name()
         */

        /*
        in this case not only admins can get the students but also students can
        got the response but we do not want to show same pages for different roles
        (meaning that we need a new api path)
        antMatchers("/", "index", "/css/*", "/js/*") due to this pattern we permit all requests
         */

        UserDetails burakUser = User.builder()
                               .username("burak")
                               .password(passwordEncoder
                               .encode("password123"))
                               .authorities(ADMIN.getGrantedAuthorities())
                               //.roles(ApplicationUserRole.ADMIN.name())
                .build();

        UserDetails tomUser = User.builder()
                .username("tom")
                .password(passwordEncoder
                        .encode("password123"))
                .authorities(ADMINTRAINEE.getGrantedAuthorities())
                //.roles(ApplicationUserRole.ADMINTRAINEE.name()) // ROLE ADMIN_TRANIEE
                .build();

        /*
        when Tom want to register a new student or update student
        response -----> {
    "timestamp": "2022-09-15T06:51:21.098+00:00",
    "status": 403,
    "error": "Forbidden",
    "path": "/management/api/v1/students/"
}
         */

        return new InMemoryUserDetailsManager(
                beyzoskayaUser,
                burakUser,
                tomUser
        );
    }
    /*
    when configuring the request we can gave any pattern that we want
    after giving a patter we need the permitAll patterns
     */


    /*
    when we did not do any login with token that given by spring
    we get the status 401 ---> Unauthorized request
    so when selected the basic auth and gave any username with spring token
    then we get the student that we want via id
     */
}

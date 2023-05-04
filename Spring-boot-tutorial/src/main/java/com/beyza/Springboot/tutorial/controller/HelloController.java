package com.beyza.Springboot.tutorial.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
technically this helloController is not a simple component
it is actually a RESOURCE
so it has a another annotation CONTROLLER
controller has also component annotation
if we use RestController instead of Controller
it should say two thing
----------------------------------------------------------
1- this is a controller like resource
2- this has always have a response body
 */

/*
When someone go to the localhost:8080
see the writing Welcome to Beyzoskaya's page!
because we gave the reference point of /
"/" ----> every time hit the site (also this is an endpoint)
 */

/*
instead of  @RequestMapping(value = "/" , method = RequestMethod.GET) we can use
----> @GetMapping("/")
 */

/*
in Spring example ---> Controller is in Presentation Layer
                  ---> Service is in Application Layer
                  ---> Repository is in Data Layer
 */
@RestController
public class HelloController {

    @Value("${welcome.message}")
    private String welcomeMessage;

    @GetMapping("/")
    public String helloWorld(){
        return welcomeMessage;
    }
}

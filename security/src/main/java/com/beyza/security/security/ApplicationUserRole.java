package com.beyza.security.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.beyza.security.security.ApplicationUserPermisson.*;


public enum ApplicationUserRole {
    STUDENT(Sets.newHashSet()),  // this HashSet is empty because Student has no any permission
    ADMIN(Sets.newHashSet(
            COURSE_READ,
            COURSE_WRITE,
            STUDENT_READ,
            STUDENT_WRITE)),
    ADMINTRAINEE(Sets.newHashSet(STUDENT_READ, COURSE_READ));

    private final Set<ApplicationUserPermisson> permissions;

    ApplicationUserRole(Set<ApplicationUserPermisson> permissions) {
        this.permissions = permissions;

    }

    public Set<ApplicationUserPermisson> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
      Set<SimpleGrantedAuthority> permissions =
              getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission())).collect(Collectors.toSet());

      permissions.add(new SimpleGrantedAuthority("ROLE_"+ this.name()));

      return permissions;
    }
}

/*
User role'leri ve bu rollere verilen belli izinler var
aynı bir siteye giris yaptıtımız zamanki gibi
 */
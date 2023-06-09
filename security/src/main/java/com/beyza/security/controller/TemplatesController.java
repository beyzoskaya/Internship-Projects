package com.beyza.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class TemplatesController {

    @GetMapping("/login")
    public String getLoginView() {
        return "login";
        // this return login is exactly same name with templates ---> login.html
    }

    @GetMapping("/api/courses")
    public String getCourses() {
        return "courses";
    }
}

package com.beyza.security.student;


import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    private static final List<Student> STUDENTS = Arrays.asList(
            new Student(1,"James Bond"),
            new Student(2,"Maria Jones"),
            new Student(3,"Anna Smith")
    );

    @GetMapping (path = "{studentId}")
    public Student getStudent(@PathVariable("studentId") Integer studentId){

        return STUDENTS.stream()
                .filter(student -> studentId.equals(student.getStudentId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Student " + studentId + "does not exist! "));
    }

    /*
    for basic auth we have a client and client want to access some requests
    request header içerisinde username ve password'ün tutuldugu base-64 formatı var
     */
}

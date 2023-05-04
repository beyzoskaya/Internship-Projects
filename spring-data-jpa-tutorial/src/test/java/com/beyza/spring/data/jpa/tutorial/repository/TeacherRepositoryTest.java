package com.beyza.spring.data.jpa.tutorial.repository;

import com.beyza.spring.data.jpa.tutorial.entity.Course;
import com.beyza.spring.data.jpa.tutorial.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void saveTeacher() {

        Course courseIe =
                Course.builder()
                        .credit(6)
                        .title("IE-343")
                        .build();

        Course courseJava =
                Course.builder()
                        .credit(8)
                        .title("Java")
                        .build();

        Teacher teacher =
                Teacher.builder()
                        .firstName("Beyza")
                        .lastName("Kaya")
                        //.courses(List.of(courseIe,courseJava))
                        .build();

        teacherRepository.save(teacher);
    }
}
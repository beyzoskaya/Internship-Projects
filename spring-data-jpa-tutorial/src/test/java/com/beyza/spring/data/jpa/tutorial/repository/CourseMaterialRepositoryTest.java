package com.beyza.spring.data.jpa.tutorial.repository;

import com.beyza.spring.data.jpa.tutorial.entity.Course;
import com.beyza.spring.data.jpa.tutorial.entity.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseMaterialRepositoryTest {

    @Autowired
    private CourseMaterialRepository courseMaterialRepository;

    @Test
    public void SaveCourseMaterial() {
        Course course = Course.builder()
                .title("SEC-101")
                .credit(6)
                .build();

        /*
        course material objesi olustururkern eger course vermezsek test hata verir cünkü
        entity içerisinde oneToOne mapping için optional'ı kabul etmedik ----> optional = false
        course vermedigimiz takdirde transaction yapılamıyor
         */

        CourseMaterial courseMaterial =
                CourseMaterial.builder()
                        .url("www.youtube.com")
                        .course(course)
                        .build();

        courseMaterialRepository.save(courseMaterial);

        /*
        When we did not change the test it fails and says
        object references an unsaved transient instance
        in this case we want to save CourseMaterial which has course's attributes but
        we do not have any saved course now
         */

        /*
        ıf we do not get any failure
        we need to add cascade to the CourseMterial's entity
         */

    }
    @Test
    public void printAllCourses() {
        List<CourseMaterial> courseMaterials =
                courseMaterialRepository.findAll();

        System.out.println("courseMaterials " + courseMaterials);


    }
}
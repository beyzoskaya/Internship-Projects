package com.beyza.spring.data.jpa.tutorial.repository;

import com.beyza.spring.data.jpa.tutorial.entity.Course;
import com.beyza.spring.data.jpa.tutorial.entity.Student;
import com.beyza.spring.data.jpa.tutorial.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository repository;

    @Test
    public void printCourses() {
        List<Course> courses =
                repository.findAll();
        System.out.println("Courses: " + courses);
    }

    @Test
    public void saveCourseWithTeacher() {
        Teacher teacher =
                Teacher.builder()
                        .firstName("Erhun")
                        .lastName("Kundakcıoglu")
                        .build();
        Course course =
                Course.builder()
                        .title("IE-246")
                        .credit(8)
                        .teacher(teacher)
                        .build();

        repository.save(course);
    }

    @Test
    public void pagePaginationTest() {
        Pageable firstPageWithThreeRecords =
                PageRequest.of(0, 3);

        Pageable secondPageWithTwoRecords =
                PageRequest.of(1, 2);

        List<Course> courses =
                repository.findAll(firstPageWithThreeRecords)
                        .getContent();

        Long totalElements =
                repository.findAll(firstPageWithThreeRecords)
                        .getTotalElements();

        int totalPages =
                repository.findAll(firstPageWithThreeRecords)
                        .getTotalPages();

        System.out.println("courses " + courses);
        System.out.println("totalElements = " + totalElements);
        System.out.println("totalPages = " + totalPages);
    }

    @Test
    public void findAllSorting() {
        Pageable sortByTitle = PageRequest.of(
                0,
                2,
                Sort.by("title")
        );

        Pageable sortByCreditDesc = PageRequest.of(
                0,
                2,
                Sort.by("credit").descending()
        );

        Pageable sortByTitleAndCreditDesc = PageRequest.of(
                0,
                2,
                Sort.by("title").descending()
                        .and(Sort.by("credit").descending())
        );

        List<Course> courses =
                repository.findAll(sortByTitle).getContent();
        System.out.println("courses = " + courses);
    }


    @Test
    public void printfindByTitleContaining() {

        Pageable firstPageTenRecords = PageRequest.of(0,1);
        List<Course> courses = repository.findByTitleContaining("D",firstPageTenRecords).getContent();
        System.out.println("courses = " + courses);
    }


    @Test
    public void saveCourseWithStudentAndTeacher() {
        Teacher teacher = Teacher.builder()
                .firstName("Nurcan")
                .lastName("Kaya")
                .build();

        Student student =
                Student.builder()
                        .firstName("Huseyin")
                        .lastName("Kaya")
                        .emailId("huseyin@gmail")
                        .build();

        Course course =
                Course.builder()
                        .title("AI")
                        .credit(12)
                        .teacher(teacher)
                        .build();

        course.addStudent(student);
        repository.save(course);

    }

}
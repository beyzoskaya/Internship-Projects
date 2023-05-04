package com.beyza.spring.data.jpa.tutorial.repository;

import com.beyza.spring.data.jpa.tutorial.entity.Guardian;
import com.beyza.spring.data.jpa.tutorial.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/*
when we add @DataJpaTest annotation this is only testing the repository layer then flus the data
but database is not update
now we want to affect the db so we do not want to use @DataJpaTest
 */

@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent() {

        Student student = Student.builder()
                .emailId("beyza@gmail.com")
                .firstName("beyza")
                .lastName("kaya")
                //.guardianName("burak")
                //.guardianEmail("burak@gmail.com")
                //.guardianMobile("666666")
                .build();

        studentRepository.save(student);

    }

    @Test
    public void saveStudentWithGuardian() {

        Guardian guardian = Guardian.builder()
                .name("Beyza")
                .email("beyzahanım@gmail")
                .mobile("99999999999")
                .build();

        Student student = Student.builder()
                .firstName("Burak")
                .emailId("burakbey@gmail")
                .lastName("aygoren")
                .guardian(guardian)
                .build();

        studentRepository.save(student);
    }

    @Test
    public void printStudentByFirstName() {
        List<Student> studentList =
                studentRepository.findByFirstName("Burak");

        System.out.println("students= " + studentList);

    }

    @Test
    public void printStudentByFirstNameContaining() {
        List<Student> studentList =
                studentRepository.findByFirstNameContaining("B");

        System.out.println("Students whose first name starts with B" + studentList);
    }

    @Test
    public void printStudentBasedOnGuardianName() {

        List<Student> studentList =
                studentRepository.findByGuardianName("Beyza");

        System.out.println("students" +studentList);
    }

    @Test
    public void printgetStudentByEmailAddress() {
       Student student =
               studentRepository.getStudentByEmailAddress("burakbey@gmail");
        System.out.println(student);

    }

    @Test
    public void printgetFirstNameByEmailAddress() {
        String studenFirstName =
                studentRepository.getStudentFirstNameByEmailAddress("burakbey@gmail");
        System.out.println("student first name " + studenFirstName);
    }

    @Test
    public void printgetStudentByEmailAddressNative() {
        Student student =
                studentRepository.getStudentByEmailAddressNative("burakbey@gmail");
        System.out.println("student" + student);

    }

    @Test
    public void printgetStudentByEmailAddressNativeNamedParam() {
        Student student =
                studentRepository.getStudentByEmailAddressNativeNamedParam("burakbey@gmail");
        System.out.println("student "+ student);
    }

    @Test
    public void updateStudentNameByEmailIdTest() {
        studentRepository.updateStudentNameByEmailId(
                "beyzosko",
                "beyzaa@gmail.com");
    }
    @Test
    public void printAllStudents() {
        List<Student> studentList = studentRepository.findAll();

        System.out.println("studentList" + studentList);
    }



}
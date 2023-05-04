package com.beyza.Springboot.tutorial.repository;

import com.beyza.Springboot.tutorial.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
for DepartmentRepo we do not need any implementation class
because we can extend the JpaRepository class
it is already has all the methods that we are going to implement
JpaRepository takes first of all ---> Entity
                    second of all --> Entity's Primary Key type
 */

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {

    public Department findByDepartmentName(String departmentName);
    public Department findByDepartmentNameIgnoreCase(String departmentName);


}

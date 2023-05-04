package com.beyza.department.service.controller;


import com.beyza.department.service.entity.Department;
import com.beyza.department.service.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departments")
@Slf4j
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/")
    public Department saveDepartment(@RequestBody Department department){

        log.info("Inside saveDepartment method of departmentController");
        return departmentService.saveDepartment(department);
    }

    @GetMapping("/{id}")
    public Department findDepartmentById(@PathVariable("id") Long departmentId){

        log.info("Inside findDepartmentById method of departmentController");
        return departmentService.findDepartmentById(departmentId);
    }
}

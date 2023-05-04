package com.beyza.department.service.service;

import com.beyza.department.service.entity.Department;
import com.beyza.department.service.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public Department saveDepartment(Department department) {

        log.info("Inside saveDepartment method of departmentService");
        return departmentRepository.save(department);
    }


    public Department findDepartmentById(Long departmentId) {

        log.info("Inside findDepartmentById method of departmentService");
        return departmentRepository.findByDepartmentId(departmentId);
    }
}

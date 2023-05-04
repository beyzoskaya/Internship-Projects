package com.beyza.Springboot.tutorial.controller;

import com.beyza.Springboot.tutorial.entity.Department;
import com.beyza.Springboot.tutorial.error.DepartmentNotFoundException;
import com.beyza.Springboot.tutorial.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    private final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    /*
    When the json data came from the client
    we need to validate it instead of return the response for client
    so when new Department occur ----> in save level we need to validate it
    with @Valid
    (think like request came out here from the client so I need to match json data in this level)
    --------------------------------------------------------------------------------------------
     */

    /*
    adding LOGGER.info ensures that see a message from the action
    when we post the new department in Postman ----> we can see a message in Run console in intellij
    if we got status: 200
     */

    @PostMapping("/departments")
    public Department saveDepartment(@Valid @RequestBody Department department) {
        LOGGER.info("Inside saveDepartment in DepartmentController ");
        return departmentService.saveDepartment(department);

    }

    @GetMapping("/departments")
    public List<Department> fetchDepartmentList() {
        LOGGER.info("Inside fetchDepartmentList in DepartmentController ");
        return departmentService.fetchDepartmentList();
    }

    /*
    we are going to find the department for given Id
    so we need to gave id in dynamic way
    {id} ---> writing id in this way because this ensures that
    id is given by request
    also we need to gave id into the method like it is parameter
     */

    /*
    It is important to gave method's parameter path variable annotation
    because id is a json object that is given by client then it must match with
    our database's object
    ----------------------------------------------------------------------------
     */

    @GetMapping("/departments/{id}")
    public Department fetchDepartmentById(@PathVariable("id") Long departmentId) throws DepartmentNotFoundException {
        return departmentService.fetchDepartmentById(departmentId);
    }

    @DeleteMapping("/departments/{id}")
    public String deleteDepartmentById(@PathVariable("id") Long departmentId) {
        departmentService.deleteDepartmentById(departmentId);
        return "Department deleted successfully!";
    }

    @PutMapping("/departments/{id}")
    public Department updateDepartment(@PathVariable("id") Long departmentId,
                                       @RequestBody @Valid Department department) {
        return departmentService.updateDepartment(departmentId, department);

    }

    @GetMapping("/departments/name/{name}")
    public Department fetchDepartmentByName(@PathVariable("name") String departmentName) {
        return departmentService.fetchDepartmentByName(departmentName);
    }
}

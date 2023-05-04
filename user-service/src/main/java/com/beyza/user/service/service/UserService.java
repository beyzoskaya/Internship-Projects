package com.beyza.user.service.service;

import com.beyza.user.service.ValueObject.Department;
import com.beyza.user.service.ValueObject.ResponseTemplateValueObject;
import com.beyza.user.service.entity.Users;
import com.beyza.user.service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/")
    public Users saveUser(@RequestBody Users user) {
        log.info("Inside saveUser method of UserService");
        return userRepository.save(user);
    }

    public ResponseTemplateValueObject getUserWithDepartment(Long userId) {
        log.info("Inside getUserWithDepartment method of UserService");
        ResponseTemplateValueObject valueObject = new ResponseTemplateValueObject();
        Users user = userRepository.findByUserId(userId);

        Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/" + user.getDepartmentId()
                ,Department.class);

        valueObject.setUser(user);
        valueObject.setDepartment(department);

        return valueObject;
    }
}

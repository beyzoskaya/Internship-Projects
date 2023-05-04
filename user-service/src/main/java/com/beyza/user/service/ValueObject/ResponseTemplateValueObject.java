package com.beyza.user.service.ValueObject;

import com.beyza.user.service.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateValueObject {

    private Users user;
    private Department department;

}

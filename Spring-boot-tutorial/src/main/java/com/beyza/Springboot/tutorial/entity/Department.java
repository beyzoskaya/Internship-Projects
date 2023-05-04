package com.beyza.Springboot.tutorial.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long departmentId;

    /*
    Department name is important for use because we do not have any department like no name or with blank
    so we avoid client request's like blank -----> We gave a message  ----> message = "Please Add Department Name"
    -------------------------------------------------------------------------------------------------------------
     */

    @NotBlank(message = "Please Add Department Name")
    /*
    @Length(max = 5, min = 0)
    @Size(max= 10, min =0)
    @Email
    @Positive
    @Negative
    @PositiveOrZero
    @NegativeOrZero
    @Future
    @FutureOrPresent

     */
    private String departmentName;
    private String departmentAddress;
    private String departmentCode;


}

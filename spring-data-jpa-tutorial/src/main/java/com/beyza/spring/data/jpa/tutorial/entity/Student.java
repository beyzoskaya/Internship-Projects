package com.beyza.spring.data.jpa.tutorial.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/*
@SequenceGenerator ----> generator for the this entity
@GeneratedValue ----> generator for the specific attribute (like id)
 */

/*
email address is a unique attribute for every entity in this model
so we can not gave the null value for email address
due to this we added nullable = false

 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tbl_student",
        uniqueConstraints = @UniqueConstraint(
                name = "emailid_unique",
                columnNames = "email_address"
        )
)
public class Student {

    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long studentId;
    private String firstName;
    private String lastName;

    @Column(name = "email_address",
            nullable = false
    )
    private String emailId;

    @Embedded
    private Guardian guardian;

}

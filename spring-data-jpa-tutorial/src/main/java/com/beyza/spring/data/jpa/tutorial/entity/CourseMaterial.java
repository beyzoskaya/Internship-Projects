package com.beyza.spring.data.jpa.tutorial.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "course")
public class CourseMaterial {

    @Id
    @SequenceGenerator(
            name = "course_material_sequence",
            sequenceName = "course_material_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_material_sequence"

    )
    private Long courseMaterialId;
    private String url;

    /*
    we have 2 fetch types -----> LAZY (relationshipini verdigimiz diger entity bilgilerini getirmez)
                                mesela bu case için CourseMaterial'ı çagırınca Course bilgisi vermez
                          -----> EAGER(ilişkisi olan entity bilgilerini de spesifik olarak verir özel olarak çağırmayız)
     */

    /*
    JoinColumn anotasyonunu kullanma sebebimiz course material entitysi de
    course entitysi de course Id içeriyor ve course id material için bir foreign key
     */
    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "course_id",
            referencedColumnName = "courseId"
    )
    private Course course;
}

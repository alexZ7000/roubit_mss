package com.maua.roubit.shared.domain.entities; 

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Integer courseId;

    private String name;
}

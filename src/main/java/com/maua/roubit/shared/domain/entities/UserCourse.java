package com.maua.roubit.shared.domain.entities; 

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "User_Course")
@Getter
@Setter
public class UserCourse {

    @EmbeddedId
    private UserCourseId id;
    
    @ManyToOne
    @Column(name = "course_id")
    private Course courseId;
    
    @ManyToOne
    @Column(name = "user_id")
    private AppUser userId;
}

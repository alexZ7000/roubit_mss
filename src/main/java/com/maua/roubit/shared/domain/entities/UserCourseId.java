package com.maua.roubit.shared.domain.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.*;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class UserCourseId implements Serializable {

    @Column(name = "course_id")
    private String courseId;

    @Column(name = "user_id")
    private String userId;
}

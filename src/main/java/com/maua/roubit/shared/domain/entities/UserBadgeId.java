package com.maua.roubit.shared.domain.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.*;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class UserBadgeId implements Serializable {

    @Column(name = "badge_id")
    private String badgeId;

    @Column(name = "user_id")
    private String userId;
}

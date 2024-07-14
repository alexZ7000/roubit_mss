package com.maua.roubit.shared.domain.entities; 

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_badge")
@Getter
@Setter
public class UserBadge {
    @EmbeddedId
    private UserBadgeId id;

    @ManyToOne
    @JoinColumn(name = "badge_id", insertable = false, updatable = false)
    private Badge badgeId;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private Users userId;
    
}

package com.maua.roubit.shared.domain.entities; 

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "User_Badge") 
@Getter
@Setter
public class UserBadge {
    @EmbeddedId
    private UserBadgeId id;
    
    @ManyToOne
    @Column(name = "badge_id")
    private Badge badgeId;

    @ManyToOne
    @Column(name = "user_id")
    private AppUser userId;
    
}

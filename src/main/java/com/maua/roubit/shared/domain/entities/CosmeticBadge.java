package com.maua.roubit.shared.domain.entities; 

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Cosmetic_Badge") 
@Getter
@Setter
public class CosmeticBadge {

    @EmbeddedId
    private CosmeticBadgeId id;

    @ManyToOne
    @JoinColumn(name = "cosmetic_id")
    private Cosmetics cosmetics;

    @ManyToOne
    @JoinColumn(name = "badge_id")
    private Badge badge;
}

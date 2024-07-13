package com.maua.roubit.shared.domain.entities; 

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cosmetic_badge")
@Getter
@Setter
public class CosmeticBadge {

    @EmbeddedId
    private CosmeticBadgeId id;

    @ManyToOne
    @JoinColumn(name = "cosmetic_id", insertable = false, updatable = false)
    private Cosmetics cosmetics;

    @ManyToOne
    @JoinColumn(name = "badge_id", insertable = false, updatable = false)
    private Badge badge;
}

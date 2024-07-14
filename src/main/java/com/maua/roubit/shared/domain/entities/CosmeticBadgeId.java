package com.maua.roubit.shared.domain.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class CosmeticBadgeId implements Serializable {
    @Column(name = "cosmetic_id", insertable=false, updatable=false)
    private UUID cosmetics;

    @Column(name = "badge_id", insertable=false, updatable=false)
    private UUID badge;
}

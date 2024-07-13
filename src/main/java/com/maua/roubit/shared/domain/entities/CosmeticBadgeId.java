package com.maua.roubit.shared.domain.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.*;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class CosmeticBadgeId implements Serializable {

    @Column(name = "cosmetic_id", length = 36)
    private String cosmeticId;

    @Column(name = "badge_id", length = 36)
    private String badgeId;
}

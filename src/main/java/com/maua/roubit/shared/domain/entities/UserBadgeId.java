package com.maua.roubit.shared.domain.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class UserBadgeId {
    @Column(name = "badge_id", insertable = false, updatable = false)
    private UUID badgeId;

    @Column(name = "user_id", insertable = false, updatable = false)
    private UUID userId;
}

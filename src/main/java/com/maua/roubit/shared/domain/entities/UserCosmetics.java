package com.maua.roubit.shared.domain.entities; 

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_cosmetics")
@Getter
@Setter
public class UserCosmetics {
    @EmbeddedId
    private UserCosmeticsId id;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private Users userId;

    @ManyToOne
    @JoinColumn(name = "cosmetic_id", insertable = false, updatable = false)
    private Cosmetics cosmeticId;
}

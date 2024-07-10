package com.maua.roubit.shared.domain.entities; 

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "User_Cosmetics")
@Getter
@Setter
public class UserCosmetics {

    @EmbeddedId
    private UserCosmeticsId id;
    
    @ManyToOne
    @Column(name = "user_id")
    private AppUser userId;
    
    @ManyToOne
    @Column(name = "cosmetic_id")
    private Cosmetics cosmeticId;
}

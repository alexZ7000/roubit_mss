package com.maua.roubit.shared.domain.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.*;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class UserCosmeticsId implements Serializable {

    @Column(name = "cosmetic_id")
    private String cosmeticId;

    @Column(name = "user_id")
    private String userId;
}

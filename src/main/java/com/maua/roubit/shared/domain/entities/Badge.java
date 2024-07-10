package com.maua.roubit.shared.domain.entities; 

import java.util.UUID;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
public class Badge {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "badge_id", nullable = false, unique = true)
    private UUID badgeId;

    private String title;
    private String icon;
    private String rarity;
    private Integer price;
}

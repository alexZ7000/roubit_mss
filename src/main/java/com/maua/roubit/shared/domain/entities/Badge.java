package com.maua.roubit.shared.domain.entities; 

import java.util.UUID;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "badge")
@Getter
@Setter
public class Badge {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "badge_id", nullable = false)
    private UUID badgeId;

    @Column(length = 30, nullable = false)
    private String title;

    @Column()
    private String icon;

    @Column(length = 50)
    private String rarity;

    @Column()
    private Integer price;
}

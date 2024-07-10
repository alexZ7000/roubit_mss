package com.maua.roubit.shared.domain.entities; 

import java.util.UUID;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
public class Cosmetics {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cosmetic_id", nullable = false, unique = true)
    private UUID cosmeticId;
    
    @ManyToOne
    @Column(nullable = true)
    private Badge badge;
    
    private String title;
    private String icon;
    private String rarity;
    private String description;
    private Integer price;
    private java.sql.Date date;
}

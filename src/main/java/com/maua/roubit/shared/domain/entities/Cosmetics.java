package com.maua.roubit.shared.domain.entities; 

import java.util.UUID;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cosmetics")
@Getter
@Setter
public class Cosmetics {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cosmetic_id", nullable = false, unique = true)
    private UUID cosmeticId;
    
    @ManyToOne
    @JoinColumn
    private Badge badge;

    @Column(length = 50)
    private String title;

    @Column()
    private String icon;

    @Column(length = 50)
    private String rarity;

    @Column()
    private String description;

    @Column()
    private Integer price;

    @Column()
    private java.sql.Date date;
}

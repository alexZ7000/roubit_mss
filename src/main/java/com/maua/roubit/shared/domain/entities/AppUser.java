package com.maua.roubit.shared.domain.entities; 

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class AppUser {

    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false, unique = true)
    private UUID userId;
    
    @Column(name = "profile_picture")
    private String profilePicture;
    
    private String name;
    
    @Column(unique = true)
    private String username;
    
    @Column(unique = true)
    private String email;

    private String password;

    @Column(nullable = true)
    private Integer semester;

    private Integer balance;

    @Column(nullable = true)
    private java.sql.Date birthday;

    private Integer streaks;

    @ManyToOne
    @JoinColumn
    @Column(nullable = true)
    private Course course;
    
    @OneToMany
    private List<Tasks> tasks;
    
    @OneToMany
    @Column(nullable = true)
    private List<Friends> friends;
    
    @OneToMany
    @Column(nullable = true)
    private List<UserBadge> badges;
    
    @OneToMany
    @Column(nullable = true)
    private List<UserCosmetics> cosmetics;
}

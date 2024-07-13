package com.maua.roubit.shared.domain.entities;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false, unique = true, updatable = false)
    private UUID userId;

    @Column(name = "profile_picture")
    private String profilePicture;

    @Column(length = 100)
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Column(nullable = false, unique = true, length = 20)
    private String username;

    @Column(nullable = false, unique = true, length = 100)
    @Nonnull()
    @NotBlank(message = "Email is mandatory")
    @Email()
    private String email;

    @Column(nullable = false)
    private String password;

    @Column()
    private Integer semester;

    @Column()
    private Integer balance;

    @Column()
    private java.sql.Date birthday;

    @Column(nullable = false)
    private Integer streaks = 0;

    @OneToMany
    private List<Tasks> tasks;

    @ManyToMany
    @Column()
    private List<UserBadge> badges;

    @ManyToMany
    @Column()
    private List<Friends> friends;

    @ManyToMany
    @Column()
    private List<UserCosmetics> cosmetics;
}

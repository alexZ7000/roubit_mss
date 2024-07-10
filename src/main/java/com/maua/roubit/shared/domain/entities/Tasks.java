package com.maua.roubit.shared.domain.entities; 

import java.util.UUID;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
public class Tasks {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "task_id", nullable = false, unique = true)
    private UUID taskId;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser userId;
    
    private String name;
    @Column(nullable = true)
    private String description;
    @Column(nullable = true)
    private String image;
    @Column(nullable = true)
    private Long chronometer;
    @Column(nullable = true)
    private Long time;
}

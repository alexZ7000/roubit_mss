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
    @Column(name = "task_id", nullable = false, unique = true, length = 36)
    private UUID taskId;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users userId;

    @Column(length = 50, nullable = false)
    private String name;

    @Column()
    private String description;

    @Column()
    private String image;

    @Column()
    private Long chronometer;

    @Column()
    private Long time;
}

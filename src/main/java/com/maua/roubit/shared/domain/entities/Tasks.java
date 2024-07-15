package com.maua.roubit.shared.domain.entities; 

import java.util.UUID;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "tasks")
@Getter
@Setter
public class Tasks {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "task_id", nullable = false, unique = true, length = 36)
    @NotNull(message = "Task ID não pode ser null")
    private UUID taskId;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull(message = "User ID (Tasks) não poder ser null")
    private Users userId;

    @Column(length = 50, nullable = false)
    @NotBlank(message = "Task name não pode estar em branco")
    private String name;

    @Column()
    @Size(max = 255, message = "Task description pode ter no máximo 255 caracteres")
    private String description;

    @Column()
    @Size(max = 255, message = "Task image pode ter no máximo 255 caracteres")
    private String image;

    @Column()
    @PositiveOrZero(message = "Task chronometer deve ser maior ou igual a 0")
    private Long chronometer;

    @Column()
    @PositiveOrZero(message = "Task time deve ser maior ou igual a 0")
    private Long time;
}

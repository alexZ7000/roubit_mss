package com.maua.roubit.shared.domain.entities;

import com.maua.roubit.shared.domain.enums.CoursesEnum;
import com.maua.roubit.shared.utils.validators.domain.enums.ValidateEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false, unique = true, updatable = false, length = 36)
    @NotNull(message = "User ID não pode ser null")
    private UUID userId;

    @Column(name = "profile_picture")
    @Size(min = 12, max = 255, message = "Profile Picture deve ter no mínimo 12 caracteres e no máximo 255 caracteres")
    @Pattern(
            regexp = "^https://[a-zA-Z0-9.-]+(?::[0-9]+)?(?:/[^ ]*)?$",
            message = "Profile picture deve começar com https:// e seguir o formato esperado."
    )
    private String profilePicture;

    @Column(length = 100)
    @NotBlank(message = "Nome não pode estar em branco")
    @Size(min = 3, max = 100, message = "Nome deve ter no mínimo 3 caracteres e no máximo 100 caracteres")
    @Pattern(
            regexp = "^\\p{L}[\\p{L}\\s]*$",
            message = "Nome deve começar com uma letra, não pode conter números e pode incluir letras acentuadas e espaços."
    )
    private String name;

    @Column(nullable = false, unique = true, length = 20)
    @NotBlank(message = "Username não pode estar em branco")
    @Size(min = 4, max = 20, message = "Username deve ter no mínimo 4 caracteres e no máximo 20 caracteres")
    @Pattern(
            regexp = "^[a-zA-Z][a-zA-Z0-9]*$",
            message = "Username deve começar com uma letra e pode conter letras e números, mas não caracteres especiais."
    )
    private String username;

    @Column(nullable = false, unique = true, length = 100)
    @Email(message = "E-mail inválido")
    @Size(min = 5, max = 100, message = "E-mail deve ter no mínimo 5 caracteres e no máximo 100 caracteres")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "Senha não pode estar em branco")
    @Size(max = 255, message = "Senha deve ter no máximo 255 caracteres")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+={}\\[\\]|;:'\",.<>?/`~])[A-Za-z\\d!@#$%^&*()_+={}\\[\\]|;:'\",.<>?/`~]{8,}$",
            message = "Senha deve ter pelo menos uma letra maiúscula, uma letra minúscula, um número e um caractere especial, e deve ter pelo menos 8 caracteres."
    )
    private String password;

    @Column()
    @NotNull(message = "Balance não pode ser null")
    @PositiveOrZero(message = "Balance deve ser maior e igual à 0")
    private Integer balance = 0;

    @Column()
    @Past(message = "Data de nascimento tem que ser no passado")
    private java.sql.Date birthday;

    @Column(nullable = false)
    @NotNull(message = "Streaks não pode ser null")
    @PositiveOrZero(message = "Streaks ter que ser maior e igual à 0")
    private Integer streaks = 0;

    @Column()
    @Positive(message = "Semestre deve ser positivo diferente de 0")
    private Integer semester;

    @Column()
    @Enumerated(EnumType.STRING)
    @ValidateEnum(enumClass = CoursesEnum.class, message = "Curso inválido")
    private CoursesEnum course;

    @ManyToMany
    @JoinTable(
            name = "friends",
            joinColumns = @JoinColumn(name = "user1_id"),
            inverseJoinColumns = @JoinColumn(name = "user2_id")
    )
    private Set<Users> friends;
}

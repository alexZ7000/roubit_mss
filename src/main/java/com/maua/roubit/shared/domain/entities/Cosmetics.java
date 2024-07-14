package com.maua.roubit.shared.domain.entities; 

import java.util.UUID;

import com.maua.roubit.shared.domain.enums.RarityEnum;
import com.maua.roubit.shared.utils.validators.domain.enums.ValidateEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "cosmetics")
@Getter
@Setter
public class Cosmetics {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cosmetic_id", nullable = false, unique = true, length = 36)
    @NotNull(message = "Badge ID não pode ser null")
    private UUID cosmeticId;

    @Column(length = 50, nullable = false)
    @NotBlank(message = "Cosmetics title não pode estar em branco")
    @Size(max = 50, message = "Cosmetics title deve ter no máximo 50 caracteres")
    private String title;

    @Column(nullable = false)
    @NotBlank(message = "Cosmetics icon não pode estar em branco")
    @Size(max = 255, message = "Cosmetics icon deve ter no máximo 255 caracteres")
    private String icon;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @NotBlank(message = "Cosmetics rarity não pode estar em branco")
    @ValidateEnum(enumClass = RarityEnum.class, message = "Cosmetics rarity de tipo inválido")
    private RarityEnum rarity;

    @Column(nullable = false)
    @NotBlank(message = "Cosmetics description não pode estar em branco")
    @Size(max = 255, message = "Cosmetics description deve ter no máximo 255 caracteres")
    private String description;

    @Column(nullable = false)
    @NotNull(message = "Cosmetics price não pode ser null")
    @PositiveOrZero(message = "Cosmetics price tem que ser maior ou igual a 0")
    private Integer price = 0;

    @Column(nullable = false)
    @NotNull(message = "Cosmetics date não pode ser null")
    @PastOrPresent(message = "Cosmetic date tem que ser no presente ou no passado")
    private java.sql.Date date;
}

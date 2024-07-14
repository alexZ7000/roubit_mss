package com.maua.roubit.shared.domain.entities; 

import java.util.UUID;

import com.maua.roubit.shared.domain.enums.RarityEnum;
import com.maua.roubit.shared.utils.validators.domain.enums.ValidateEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "badge")
@Getter
@Setter
public class Badge {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "badge_id", unique = true, nullable = false, length = 36)
    @NotNull(message = "Badge ID não pode ser null")
    private UUID badgeId;

    @Column(length = 30, nullable = false)
    @NotBlank(message = "Badge title não pode estar em branco")
    @Size(max = 30, message = "Badge Title deve ter no máximo 30 caracteres")
    private String title;

    @Column(nullable = false)
    @NotBlank(message = "Badge icon não pode estar em branco")
    @Size(max = 255, message = "Badge icon deve ter no máximo 255 caracteres")
    private String icon;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Badge rarity não pode ser null")
    @ValidateEnum(enumClass = RarityEnum.class, message = "Badge rarity de tipo inválido")
    private RarityEnum rarity;

    @Column(nullable = false)
    @NotNull(message = "Badge price não pode ser null")
    @PositiveOrZero(message = "Badge price deve ser maior ou igual à zero")
    private Integer price;
}

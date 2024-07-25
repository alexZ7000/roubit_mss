package com.maua.roubit.shared.domain.repositories;

import com.maua.roubit.shared.domain.entities.Badge;
import com.maua.roubit.shared.domain.enums.RarityEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BadgeRepoInterface extends JpaRepository<Badge, UUID> {

    
    // Buscar por título (case-insensitive, ignorando maiúsculas e minúsculas)
    Optional<Badge> findByTitleIgnoreCase(String title);
    
    // Buscar todos os badges de uma certa raridade
    List<Badge> findByRarity(RarityEnum rarity);
    
    // Buscar todos os badges com preço menor ou igual a um valor específico
    List<Badge> findByPriceLessThanEqual(Integer price);
    
    // Buscar todos os badges com preço entre um intervalo
    List<Badge> findByPriceBetween(Integer startPrice, Integer endPrice);
    
    // Buscar todos os badges ordenados por preço (ascendente)
    List<Badge> findAllByOrderByPriceAsc();
    
    // Buscar todos os badges ordenados por preço (descendente)
    List<Badge> findAllByOrderByPriceDesc();
    
    // Contar o número de badges com uma certa raridade
    long countByRarity(RarityEnum rarity);
}

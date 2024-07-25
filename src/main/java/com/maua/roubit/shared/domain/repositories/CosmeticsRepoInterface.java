package com.maua.roubit.shared.domain.repositories;

import com.maua.roubit.shared.domain.entities.Cosmetics;
import com.maua.roubit.shared.domain.enums.RarityEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CosmeticsRepoInterface extends JpaRepository<Cosmetics, UUID> {

    Optional<Cosmetics> findByTitleIgnoreCase(String title);

    List<Cosmetics> findByRarity(RarityEnum rarity);

    List<Cosmetics> findByPriceLessThanEqual(Integer price);

    List<Cosmetics> findByPriceBetween(Integer startPrice, Integer endPrice);

    List<Cosmetics> findAllByOrderByPriceAsc();

    List<Cosmetics> findAllByOrderByPriceDesc();
    
    long countByRarity(RarityEnum rarity);

}

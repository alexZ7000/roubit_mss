package com.maua.roubit.shared.infra.repositories.UserRepository;

import com.maua.roubit.shared.domain.entities.Cosmetics;
import com.maua.roubit.shared.domain.enums.RarityEnum;
import com.maua.roubit.shared.infra.repositories.CosmeticsRepoMock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class CosmeticsRepoMockTests {
    private CosmeticsRepoMock cosmeticsRepoMock;
    private Cosmetics cosmetic1;
    private Cosmetics cosmetic2;


    @BeforeEach
    void setUp() {
        cosmeticsRepoMock = new CosmeticsRepoMock();

        cosmetic1 = new Cosmetics();
        cosmetic1.setCosmeticId(UUID.randomUUID());
        cosmetic1.setTitle("Cosmetic 1");
        cosmetic1.setIcon("icon1.png");
        cosmetic1.setRarity(RarityEnum.COMUM);
        cosmetic1.setDescription("Description 1");
        cosmetic1.setPrice(100);
        cosmetic1.setDate(Date.valueOf("2023-07-20"));

        cosmetic2 = new Cosmetics();
        cosmetic2.setCosmeticId(UUID.randomUUID());
        cosmetic2.setTitle("Cosmetic 2");
        cosmetic2.setIcon("icon2.png");
        cosmetic2.setRarity(RarityEnum.RARO);
        cosmetic2.setDescription("Description 2");
        cosmetic2.setPrice(200);
        cosmetic2.setDate(Date.valueOf("2023-08-20"));

        cosmeticsRepoMock.save(cosmetic1);
        cosmeticsRepoMock.save(cosmetic2);
    }

    @Test
    void testFindByTitleIgnoreCase() {
        Optional<Cosmetics> result = cosmeticsRepoMock.findByTitleIgnoreCase("cosmetic 1");
        assertTrue(result.isPresent());
        assertEquals("Cosmetic 1", result.get().getTitle());
    }

    @Test
    void testFindByRarity() {
        List<Cosmetics> result = cosmeticsRepoMock.findByRarity(RarityEnum.RARO);
        assertEquals(1, result.size());
        assertEquals(RarityEnum.RARO, result.get(0).getRarity());
    }

    @Test
    void testFindByPriceLessThanEqual() {
        List<Cosmetics> result = cosmeticsRepoMock.findByPriceLessThanEqual(150);
        assertEquals(1, result.size());
        assertEquals("Cosmetic 1", result.get(0).getTitle());
    }

    @Test
    void testFindByPriceBetween() {
        List<Cosmetics> result = cosmeticsRepoMock.findByPriceBetween(100, 200);
        assertEquals(2, result.size());
    }

    @Test
    void testFindAllByOrderByPriceAsc() {
        List<Cosmetics> result = cosmeticsRepoMock.findAllByOrderByPriceAsc();
        assertEquals(2, result.size());
        assertEquals("Cosmetic 1", result.get(0).getTitle());
    }

    @Test
    void testFindAllByOrderByPriceDesc() {
        List<Cosmetics> result = cosmeticsRepoMock.findAllByOrderByPriceDesc();
        assertEquals(2, result.size());
        assertEquals("Cosmetic 2", result.get(0).getTitle());
    }

    @Test
    void testCountByRarity() {
        long count = cosmeticsRepoMock.countByRarity(RarityEnum.RARO);
        assertEquals(1, count);
    }
}

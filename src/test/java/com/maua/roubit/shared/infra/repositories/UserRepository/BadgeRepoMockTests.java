package com.maua.roubit.shared.infra.repositories.UserRepository;


import com.maua.roubit.shared.domain.entities.Badge;
import com.maua.roubit.shared.domain.enums.RarityEnum;
import com.maua.roubit.shared.infra.repositories.BadgeRepoMock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class BadgeRepoMockTests {
    private BadgeRepoMock badgeRepoMock;
    private Badge badge1;
    private Badge badge2;
    private Badge badge3;

    @BeforeEach
    public void setUp() {
        badgeRepoMock = new BadgeRepoMock();
        
        badge1 = new Badge();
        badge1.setBadgeId(UUID.randomUUID());
        badge1.setTitle("Badge1");
        badge1.setRarity(RarityEnum.COMUM);
        badge1.setPrice(100);

        badge2 = new Badge();
        badge2.setBadgeId(UUID.randomUUID());
        badge2.setTitle("Badge2");
        badge2.setRarity(RarityEnum.RARO);
        badge2.setPrice(200);

        badge3 = new Badge();
        badge3.setBadgeId(UUID.randomUUID());
        badge3.setTitle("Badge3");
        badge3.setRarity(RarityEnum.EPICO);
        badge3.setPrice(300);

        badgeRepoMock.save(badge1);
        badgeRepoMock.save(badge2);
        badgeRepoMock.save(badge3);
    }

    @Test
    public void testFindByTitleIgnoreCase() {
        Optional<Badge> found = badgeRepoMock.findByTitleIgnoreCase("badge2");
        assertThat(found).isPresent();
        assertThat(found.get().getTitle()).isEqualTo("Badge2");
    }

    @Test
    public void testFindByRarity() {
        List<Badge> found = badgeRepoMock.findByRarity(RarityEnum.RARO);
        assertThat(found).hasSize(1);
        assertThat(found.get(0).getRarity()).isEqualTo(RarityEnum.RARO);
    }

    @Test
    public void testFindByPriceLessThanEqual() {
        List<Badge> found = badgeRepoMock.findByPriceLessThanEqual(200);
        assertThat(found).hasSize(2);
        assertThat(found).extracting("price").contains(100, 200);
    }

    @Test
    public void testFindByPriceBetween() {
        List<Badge> found = badgeRepoMock.findByPriceBetween(150, 250);
        assertThat(found).hasSize(1);
        assertThat(found.get(0).getPrice()).isEqualTo(200);
    }

    @Test
    public void testFindAllByOrderByPriceAsc() {
        List<Badge> found = badgeRepoMock.findAllByOrderByPriceAsc();
        assertThat(found).hasSize(3);
        assertThat(found.get(0).getPrice()).isEqualTo(100);
        assertThat(found.get(1).getPrice()).isEqualTo(200);
        assertThat(found.get(2).getPrice()).isEqualTo(300);
    }

    @Test
    public void testFindAllByOrderByPriceDesc() {
        List<Badge> found = badgeRepoMock.findAllByOrderByPriceDesc();
        assertThat(found).hasSize(3);
        assertThat(found.get(0).getPrice()).isEqualTo(300);
        assertThat(found.get(1).getPrice()).isEqualTo(200);
        assertThat(found.get(2).getPrice()).isEqualTo(100);
    }

    @Test
    public void testCountByRarity() {
        long count = badgeRepoMock.countByRarity(RarityEnum.EPICO);
        assertThat(count).isEqualTo(1);
    }
}

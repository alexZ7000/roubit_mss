package com.maua.roubit.shared.entities;

import static org.junit.jupiter.api.Assertions.*;

import com.maua.roubit.shared.domain.entities.Badge;
import com.maua.roubit.shared.domain.enums.RarityEnum;
import com.maua.roubit.shared.utils.validators.errors.ValidationUtil;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.UUID;

public class BadgeTest {

    private final Badge globalBadge = new Badge();
    private Validator validator;

    @BeforeEach
    public void setUp() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        final UUID badgeId = UUID.randomUUID();
        globalBadge.setBadgeId(badgeId);
        globalBadge.setTitle("Gold Badge");
        globalBadge.setIcon("https://example.com/gold.png");
        globalBadge.setRarity(RarityEnum.COMUM);
        globalBadge.setPrice(50);
    }

    @Test
    public void testBadge() {
        final Badge badge = new Badge();

        UUID badgeId = UUID.randomUUID();
        badge.setBadgeId(badgeId);
        badge.setTitle("Silver Badge");
        badge.setIcon("https://example.com/silver.png");
        badge.setRarity(RarityEnum.RARO);
        badge.setPrice(30);

        assertEquals(badgeId, badge.getBadgeId());
        assertEquals("Silver Badge", badge.getTitle());
        assertEquals("https://example.com/silver.png", badge.getIcon());
        assertEquals(RarityEnum.RARO, badge.getRarity());
        assertEquals(30, badge.getPrice());
    }

    @Test //title
    public void testInvalidTitle() {
        // Teste 1: Titulo em branco
        globalBadge.setTitle("");
        Map<String, String> violationMessages = ValidationUtil.validateAndGetViolations(validator, globalBadge);
        assertEquals("Badge title não pode estar em branco", violationMessages.get("title"));

        // Teste 2: Titulo muito longo
        globalBadge.setTitle("This is a very long title that exceeds the maximum allowed length");
        violationMessages = ValidationUtil.validateAndGetViolations(validator, globalBadge);
        assertEquals("Badge Title deve ter no máximo 30 caracteres", violationMessages.get("title"));
    }

    @Test //icon
    public void testInvalidIcon() {
        // Teste 1: Icone em branco
        globalBadge.setIcon("");
        Map<String, String> violationMessages = ValidationUtil.validateAndGetViolations(validator, globalBadge);
        assertEquals("Badge icon não pode estar em branco", violationMessages.get("icon"));
    
        // Teste 2: Icone excede o tamanho máximo
        String longIconUrl = "https://example.com/this/is/a/very/long/icon/url/that/exceeds/the/maximum/allowed/length/" + 
        "for/an/icon/field/and/is/going/beyond/255/characters.pnghttps://example.com/this/is/a/very/long/icon/url/that/" + 
        "exceeds/the/maximum/allowed/lehttps://example.com/this/is/a/very/long/icon/url/that/exceeds/the/^";
        globalBadge.setIcon(longIconUrl);
        violationMessages = ValidationUtil.validateAndGetViolations(validator, globalBadge);
        assertEquals("Badge icon deve ter no máximo 255 caracteres", violationMessages.get("icon"));
    }

    @Test //rarity
    public void testInvalidRarity() {
        // Teste: Rarity nulo
        globalBadge.setRarity(null);
        Map<String, String> violationMessages = ValidationUtil.validateAndGetViolations(validator, globalBadge);
        assertEquals("Badge rarity não pode ser null", violationMessages.get("rarity"));
    }

    @Test //price 
    public void testInvalidPrice() {
        // Teste: Preço negativo
        globalBadge.setPrice(-10);
        Map<String, String> violationMessages = ValidationUtil.validateAndGetViolations(validator, globalBadge);
        assertEquals("Badge price deve ser maior ou igual à zero", violationMessages.get("price"));

        // Teste 2: Preço nulo
        globalBadge.setPrice(null);
        violationMessages = ValidationUtil.validateAndGetViolations(validator, globalBadge);
        assertEquals("Badge price não pode ser null", violationMessages.get("price"));        
    }
}

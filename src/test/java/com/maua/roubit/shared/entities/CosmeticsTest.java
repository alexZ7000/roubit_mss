package com.maua.roubit.shared.entities;

import static org.junit.jupiter.api.Assertions.*;

import com.maua.roubit.shared.domain.entities.Cosmetics;
import com.maua.roubit.shared.domain.enums.RarityEnum;
import com.maua.roubit.shared.utils.validators.errors.ValidationUtil;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.UUID;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CosmeticsTest {

    private final Cosmetics globalCosmetic = new Cosmetics();
    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        UUID cosmeticId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        globalCosmetic.setCosmeticId(cosmeticId);
        globalCosmetic.setTitle("Cosmetic Title");
        globalCosmetic.setIcon("https://example.com/icon.png");
        globalCosmetic.setRarity(RarityEnum.RARO);
        globalCosmetic.setDescription("This is a cosmetic description.");
        globalCosmetic.setPrice(50);
        globalCosmetic.setDate(Date.valueOf("2023-07-15"));
    }

    @Test
    public void testValidCosmetic() {
        Cosmetics cosmetic = new Cosmetics();
        UUID cosmeticId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        cosmetic.setCosmeticId(cosmeticId);
        cosmetic.setTitle("Cosmetic Title");
        cosmetic.setIcon("https://example.com/icon.png");
        cosmetic.setRarity(RarityEnum.COMUM);
        cosmetic.setDescription("This is a cosmetic description.");
        cosmetic.setPrice(50);
        cosmetic.setDate(Date.valueOf("2023-07-15"));

        assertEquals(cosmeticId, cosmetic.getCosmeticId());
        assertEquals("Cosmetic Title", cosmetic.getTitle());
        assertEquals("https://example.com/icon.png", cosmetic.getIcon());
        assertEquals(RarityEnum.COMUM, cosmetic.getRarity());
        assertEquals("This is a cosmetic description.", cosmetic.getDescription());
        assertEquals(50, cosmetic.getPrice());
        assertEquals(Date.valueOf("2023-07-15"), cosmetic.getDate());
    }

    @Test //title
    public void testInvalidTitle() {
        // Teste 1: Título em branco
        globalCosmetic.setTitle("");
        Map<String, String> violationMessages = ValidationUtil.validateAndGetViolations(validator, globalCosmetic);
        assertEquals("Cosmetics title não pode estar em branco", violationMessages.get("title"));

        // Teste 2: Título excede o tamanho máximo
        globalCosmetic.setTitle("This is a very long title that exceeds the maximum allowed length for a cosmetics title field in the database.");
        violationMessages = ValidationUtil.validateAndGetViolations(validator, globalCosmetic);
        assertEquals("Cosmetics title deve ter no máximo 50 caracteres", violationMessages.get("title"));
    }

    @Test //icon
    public void testInvalidIcon() {
        // Teste 1: Ícone em branco
        globalCosmetic.setIcon("");
        Map<String, String> violationMessages = ValidationUtil.validateAndGetViolations(validator, globalCosmetic);
        assertEquals("Cosmetics icon não pode estar em branco", violationMessages.get("icon"));

        // Teste 2: Ícone excede o tamanho máximo
        String longIconUrl = "https://example.com/this/is/a/very/long/icon/url/that/exceeds/the/maximum/allowed/length/for/an/icon/field.pnghttps://example." + 
        "com/this/is/a/very/long/icon/url/that/exceeds/the/maximum/allowed/length/for/an/icon/field.pnghttps://example.com/this/is/a/very/long/icon/url/that/";
        globalCosmetic.setIcon(longIconUrl);
        violationMessages = ValidationUtil.validateAndGetViolations(validator, globalCosmetic);
        assertEquals("Cosmetics icon deve ter no máximo 255 caracteres", violationMessages.get("icon"));
    }

    @Test //description
    public void testInvalidDescription() {
        // Teste 1: Descrição em branco
        globalCosmetic.setDescription("");
        Map<String, String> violationMessages = ValidationUtil.validateAndGetViolations(validator, globalCosmetic);
        assertEquals("Cosmetics description não pode estar em branco", violationMessages.get("description"));

        // Teste 2: Descrição excede o tamanho máximo
        globalCosmetic.setDescription("This is a very long description that exceeds the maximum allowed length for a cosmetics description field in" + 
        "the database.This is a very long description that exceeds the maximum allowed length for a cosmetics description field in the database.This is" + 
        "a very long description that exceeds the maximum allowed length for a cosmetics description field in the database.This is a very long description" + 
        "that exceeds the maximum allowed length for a cosmetics description field in the database.");
        violationMessages = ValidationUtil.validateAndGetViolations(validator, globalCosmetic);
        assertEquals("Cosmetics description deve ter no máximo 255 caracteres", violationMessages.get("description"));
    }

    @Test //price 
    public void testInvalidPrice() {
        // Teste: Preço negativo
        globalCosmetic.setPrice(-10);
        Map<String, String> violationMessages = ValidationUtil.validateAndGetViolations(validator, globalCosmetic);
        assertEquals("Cosmetics price tem que ser maior ou igual a 0", violationMessages.get("price"));
    }

    @Test //date
    public void testInvalidDate() {
        // Teste: Data futura
        globalCosmetic.setDate(Date.valueOf("2025-01-01"));
        Map<String, String> violationMessages = ValidationUtil.validateAndGetViolations(validator, globalCosmetic);
        assertEquals("Cosmetic date tem que ser no presente ou no passado", violationMessages.get("date"));
    }
}

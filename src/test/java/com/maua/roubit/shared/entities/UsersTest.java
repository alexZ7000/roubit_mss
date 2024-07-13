package com.maua.roubit.shared.entities;

import static org.junit.jupiter.api.Assertions.*;

import com.maua.roubit.shared.domain.entities.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;

import java.util.*;
import java.sql.Date;

public class UsersTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testUserEntity() {
        Users user = new Users();

        UUID userId = UUID.randomUUID();
        user.setUserId(userId);
        user.setProfilePicture("profilePic.png");
        user.setName("John Doe");
        user.setUsername("johndoe");
        user.setEmail("johndoe@example.com");
        user.setPassword("securepassword");
        user.setSemester(1);
        user.setBalance(100);
        user.setBirthday(Date.valueOf("2000-01-01"));
        user.setStreaks(5);
        user.setTasks(new ArrayList<>());
        user.setBadges(new ArrayList<>());
        user.setFriends(new ArrayList<>());
        user.setCosmetics(new ArrayList<>());

        assertEquals(userId, user.getUserId());
        assertEquals("profilePic.png", user.getProfilePicture());
        assertEquals("John Doe", user.getName());
        assertEquals("johndoe", user.getUsername());
        assertEquals("johndoe@example.com", user.getEmail());
        assertEquals("securepassword", user.getPassword());
        assertEquals(1, user.getSemester());
        assertEquals(100, user.getBalance());
        assertEquals(Date.valueOf("2000-01-01"), user.getBirthday());
        assertEquals(5, user.getStreaks());
        assertNotNull(user.getTasks());
        assertNotNull(user.getBadges());
        assertNotNull(user.getFriends());
        assertNotNull(user.getCosmetics());
    }

    @Test
    public void testUserValidation() {
        Users user = new Users();
        user.setUserId(UUID.randomUUID());
        user.setName("Ale");
        user.setUsername("jd");
        user.setEmail("fuedassegmail.c");
        user.setPassword("");
        user.setSemester(-1);
        user.setBalance(-100);
        user.setStreaks(-5);

        Set<ConstraintViolation<Users>> violations = validator.validate(user);

        if (violations.isEmpty()) {
            assertTrue(violations.isEmpty());
        }

        Map<String, String> violationMessages = new HashMap<>();
        for (ConstraintViolation<Users> violation : violations) {
            String propertyPath = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            violationMessages.put(propertyPath, message);
            System.out.println("Campo: " + propertyPath + ", Erro: " + message);
        }

        assertEquals("deve ser um endereço de e-mail bem formado", violationMessages.get("email"));
    }
}

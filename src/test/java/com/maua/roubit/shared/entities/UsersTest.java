package com.maua.roubit.shared.entities;

import static org.junit.jupiter.api.Assertions.*;

import com.maua.roubit.shared.domain.entities.Users;
import com.maua.roubit.shared.utils.ValidationUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.*;
import java.sql.Date;

public class UsersTest {

    private final Users globalUser = new Users();
    private Validator validator;

    @BeforeEach
    public void setUp() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        final UUID userId = UUID.randomUUID();
        globalUser.setUserId(userId);
        globalUser.setProfilePicture("https://profilePic.png");
        globalUser.setName("John Doe");
        globalUser.setUsername("johndoe");
        globalUser.setEmail("johndoe@example.com");
        globalUser.setPassword("Secure1@password");
        globalUser.setSemester(1);
        globalUser.setBalance(100);
        globalUser.setBirthday(Date.valueOf("2000-01-01"));
        globalUser.setStreaks(5);
        globalUser.setTasks(new ArrayList<>());
        globalUser.setBadgesId(new ArrayList<>());
        globalUser.setFriendsId(new ArrayList<>());
        globalUser.setCosmeticsId(new ArrayList<>());
    }

    @Test
    public void testUser() {
        final Users user = new Users();

        UUID userId = UUID.randomUUID();
        user.setUserId(userId);
        user.setProfilePicture("https://profilePic.png");
        user.setName("John Doe");
        user.setUsername("johndoe");
        user.setEmail("johndoe@example.com");
        user.setPassword("Secure1@password");
        user.setSemester(1);
        user.setBalance(100);
        user.setBirthday(Date.valueOf("2000-01-01"));
        user.setStreaks(5);
        user.setTasks(new ArrayList<>());
        user.setBadgesId(new ArrayList<>());
        user.setFriendsId(new ArrayList<>());
        user.setCosmeticsId(new ArrayList<>());

        assertEquals(userId, user.getUserId());
        assertEquals("https://profilePic.png", user.getProfilePicture());
        assertEquals("John Doe", user.getName());
        assertEquals("johndoe", user.getUsername());
        assertEquals("johndoe@example.com", user.getEmail());
        assertEquals("Secure1@password", user.getPassword());
        assertEquals(1, user.getSemester());
        assertEquals(100, user.getBalance());
        assertEquals(Date.valueOf("2000-01-01"), user.getBirthday());
        assertEquals(5, user.getStreaks());
        assertNotNull(user.getTasks());
        assertNotNull(user.getBadgesId());
        assertNotNull(user.getFriendsId());
        assertNotNull(user.getCosmeticsId());
    }

    @Test
    public void testUserEmailIsNull() {
        globalUser.setEmail("");
        Map<String, String> violationMessages = ValidationUtil.validateAndGetViolations(validator, globalUser);
        assertEquals("E-mail deve ter no mínimo 5 caracteres e no máximo 100 caracteres", violationMessages.get("email"));
    }

    @Test
    public void testUserEmailIsInvalid() {
        globalUser.setEmail("sdghvavudvasd");
        Map<String, String> violationMessages = ValidationUtil.validateAndGetViolations(validator, globalUser);
        assertEquals("E-mail inválido", violationMessages.get("email"));
    }
}

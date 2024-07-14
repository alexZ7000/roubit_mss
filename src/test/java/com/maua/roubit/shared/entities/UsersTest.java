package com.maua.roubit.shared.entities;

import static org.junit.jupiter.api.Assertions.*;

import com.maua.roubit.shared.domain.entities.Users;
import com.maua.roubit.shared.domain.enums.CoursesEnum;
import com.maua.roubit.shared.utils.validators.domain.entities.UUIDValidator;
import com.maua.roubit.shared.utils.validators.errors.ValidationUtil;
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

        final UUID userId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
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
        globalUser.setCourse(CoursesEnum.DESIGN);
    }

    @Test
    public void testUser() {
        final Users user = new Users();

        UUID userId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        user.setUserId(userId);
        user.setProfilePicture("https://github.com/alexZ7000.png");
        user.setName("Alessandro Lima");
        user.setUsername("alexZ7000");
        user.setEmail("alessandrolima@gmail.com");
        user.setPassword("Secure1@password");
        user.setBalance(100);
        user.setBirthday(Date.valueOf("2000-01-01"));
        user.setStreaks(5);
        user.setSemester(1);
        user.setCourse(CoursesEnum.CIENCIA_DA_COMPUTACAO);

        assertEquals(userId, user.getUserId());
        assertEquals("https://github.com/alexZ7000.png", user.getProfilePicture());
        assertEquals("Alessandro Lima", user.getName());
        assertEquals("alexZ7000", user.getUsername());
        assertEquals("alessandrolima@gmail.com", user.getEmail());
        assertEquals("Secure1@password", user.getPassword());
        assertEquals(100, user.getBalance());
        assertEquals(Date.valueOf("2000-01-01"), user.getBirthday());
        assertEquals(5, user.getStreaks());
        assertEquals(1, user.getSemester());
        assertEquals(CoursesEnum.CIENCIA_DA_COMPUTACAO, user.getCourse());
    }

    @Test
    public void testUserIDIsValid() {
        String userId = !UUIDValidator.validateUUID(
                "userId",
                "123e4567-e89b-12d3-a456-42661417400"
        ) ? "UUID inválido" : "UUID Válido";
        assertEquals("UUID inválido", userId);
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

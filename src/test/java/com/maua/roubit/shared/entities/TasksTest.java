package com.maua.roubit.shared.entities;

import static org.junit.jupiter.api.Assertions.*;

import com.maua.roubit.shared.domain.entities.Tasks;
import com.maua.roubit.shared.domain.entities.Users;
import com.maua.roubit.shared.domain.enums.CoursesEnum;
import com.maua.roubit.shared.utils.validators.errors.ValidationUtil;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.Map;
import java.util.UUID;

public class TasksTest {

    private Validator validator;
    private Tasks globalTask;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        globalTask = new Tasks();
        globalTask.setTaskId(UUID.randomUUID());
        globalTask.setUserId(createTestUser());
        globalTask.setName("Task Name");
        globalTask.setDescription("Task Description");
        globalTask.setImage("https://example.com/task.png");
        globalTask.setChronometer(100L);
        globalTask.setTime(50L);
    }

    private Users createTestUser() {
        Users user = new Users();
        user.setUserId(UUID.randomUUID());
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
        return user;
    }

    @Test
    public void testValidTask() {
        Tasks task = new Tasks();
        UUID taskId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        task.setTaskId(taskId);
        task.setUserId(globalTask.getUserId());
        task.setName("Task Name");
        task.setDescription("Task Description");
        task.setImage("https://example.com/task.png");
        task.setChronometer(100L);
        task.setTime(50L);

        assertEquals(taskId, task.getTaskId());
        assertEquals("Task Name", task.getName());
        assertEquals("Task Description", task.getDescription());
        assertEquals("https://example.com/task.png", task.getImage());
        assertEquals(100L, task.getChronometer());
        assertEquals(50L, task.getTime());
    }

    @Test //name
    public void testInvalidName() {
        // Test 1: Nome em branco
        globalTask.setName("");
        Map<String, String> violationMessages = ValidationUtil.validateAndGetViolations(validator, globalTask);
        assertEquals("Task name não pode estar em branco", violationMessages.get("name"));

        // Test 2: Nome ultrapassa o tamanho
        globalTask.setName("This is a very long task name that exceeds the maximum allowed length for a task name field in the databasetask name that exceeds " + 
        "the maximum allowed length for a task name field task name that exceeds the maximum allowed length for a task name field task name that exceeds the");
        violationMessages = ValidationUtil.validateAndGetViolations(validator, globalTask);
        assertEquals("Task name pode ter no máximo 50 caracteres", violationMessages.get("name"));
    }

    @Test //description
    public void testInvalidDescription() {
        // Teste: Description ultrapassa o tamanho
        globalTask.setDescription("This is a very long task description that exceeds the maximum allowed length for a task description" +
        "field in the database. This is a very long task description that exceeds the maximum allowed length for a task description field" +
        "in the database. This is a very long task description that exceeds the maximum allowed length for a task description field in the database.");
        Map<String, String> violationMessages = ValidationUtil.validateAndGetViolations(validator, globalTask);
        assertEquals("Task description pode ter no máximo 255 caracteres", violationMessages.get("description"));
    }

    @Test //image
    public void testInvalidImage() {
        // Teste: Imagem ultrapassa o tamanho
        String longImageUrl = "https://example.com/this/is/a/very/long/image/url/that/exceeds/the/maximum/allowed/length/for/an/image/field.png" + 
        "https://example.com/this/is/a/very/long/image/https://example.com/this/is/a/very/long/image/https://example.com/this/is/a/very/long/image/" + 
        "https://example.com/this/is/a/very/long/image/https://example.com/this/is/a/very/long/image/https://example.com/this/is/a/very/long/image/";
        globalTask.setImage(longImageUrl);
        Map<String, String> violationMessages = ValidationUtil.validateAndGetViolations(validator, globalTask);
        assertEquals("Task image pode ter no máximo 255 caracteres", violationMessages.get("image"));
    }

    @Test
    public void testInvalidChronometer() {
        // Test: Cronometro negativo
        globalTask.setChronometer(-10L);
        Map<String, String> violationMessages = ValidationUtil.validateAndGetViolations(validator, globalTask);
        assertEquals("Task chronometer deve ser maior ou igual a 0", violationMessages.get("chronometer"));
    }

    @Test
    public void testInvalidTime() {
        // Test: Tempo negativo
        globalTask.setTime(-5L);
        Map<String, String> violationMessages = ValidationUtil.validateAndGetViolations(validator, globalTask);
        assertEquals("Task time deve ser maior ou igual a 0", violationMessages.get("time"));
    }
}

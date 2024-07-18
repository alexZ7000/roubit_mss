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
    
    @Test //userId 
    public void testInvalidUserID() {
        String userId = !UUIDValidator.validateUUID(
                "userId",
                "123e4567-e89b-12d3-a456-42661417400"
        ) ? "UUID inválido" : "UUID Válido";
        assertEquals("UUID inválido", userId);
    }

    @Test //profilePicture 
    public void testInvalidProfilePicture() {
        // Teste 1: Profile picture com tamanho inválido
        globalUser.setProfilePicture("https://u4h"); 
        Map<String, String> violationMessages = ValidationUtil.validateAndGetViolations(validator, globalUser);
        assertEquals("Profile Picture deve ter no mínimo 12 caracteres e no máximo 255 caracteres", violationMessages.get("profilePicture"));
    
        // Teste 2: Profile picture não começa com https://
        globalUser.setProfilePicture("htÇslfhjsr74fheuru384yjbjrywuwu4h"); 
        violationMessages = ValidationUtil.validateAndGetViolations(validator, globalUser);   
        assertEquals("Profile picture deve começar com https:// e seguir o formato esperado.", violationMessages.get("profilePicture"));
    }

    @Test //name 
    public void testInvalidName() {
        // Teste 1: Nome com menos de 3 caracteres
        globalUser.setName("ab");
        Map<String, String>violationMessages = ValidationUtil.validateAndGetViolations(validator, globalUser);
        assertEquals("Nome deve ter no mínimo 3 caracteres e no máximo 100 caracteres", violationMessages.get("name"));

        // Teste 2: Nome com caracteres inválidos
        globalUser.setName("123abc");
        violationMessages = ValidationUtil.validateAndGetViolations(validator, globalUser);
        assertEquals("Nome deve começar com uma letra, não pode conter números e pode incluir letras acentuadas e espaços.", violationMessages.get("name"));
    }

    @Test //username
    public void testInvalidUsername() {
        // Teste 1: Username com menos de 4 caracteres
        globalUser.setUsername("abc");
        Map<String, String>violationMessages = ValidationUtil.validateAndGetViolations(validator, globalUser);
        assertEquals("Username deve ter no mínimo 4 caracteres e no máximo 20 caracteres", violationMessages.get("username"));

        // Teste 2: Username com mais de 20 caracteres
        globalUser.setUsername("abcdefghijklmnopqrsthji");
        violationMessages = ValidationUtil.validateAndGetViolations(validator, globalUser);
        assertEquals("Username deve ter no mínimo 4 caracteres e no máximo 20 caracteres", violationMessages.get("username"));

        // Teste 3: Username com caracteres especiais
        globalUser.setUsername("user@name");
        violationMessages = ValidationUtil.validateAndGetViolations(validator, globalUser);
        assertEquals("Username deve começar com uma letra e pode conter letras e números, mas não caracteres especiais.", violationMessages.get("username"));
    }

    @Test //email
    public void testInvalidEmail() {
        // Teste 1: Email em branco 
        globalUser.setEmail("");
        Map<String, String> violationMessages = ValidationUtil.validateAndGetViolations(validator, globalUser);
        assertEquals("E-mail deve ter no mínimo 5 caracteres e no máximo 100 caracteres", violationMessages.get("email"));
        
        // Teste 2: Email inválido 
        globalUser.setEmail("sdghvavudvasd");
        violationMessages = ValidationUtil.validateAndGetViolations(validator, globalUser);
        assertEquals("E-mail inválido", violationMessages.get("email"));
    }

    @Test //password 
    public void testInvalidPassword() {
        // Teste 1: Password sem letra maiúscula
        globalUser.setPassword("password1!");
        Map<String, String> violationMessages = ValidationUtil.validateAndGetViolations(validator, globalUser);
        assertEquals("Senha deve ter pelo menos uma letra maiúscula, uma letra minúscula, um número e um caractere especial, e deve ter pelo menos 8 caracteres.", violationMessages.get("password"));

        // Teste 2: Password sem número
        globalUser.setPassword("Password!");
        violationMessages = ValidationUtil.validateAndGetViolations(validator, globalUser);
        assertEquals("Senha deve ter pelo menos uma letra maiúscula, uma letra minúscula, um número e um caractere especial, e deve ter pelo menos 8 caracteres.", violationMessages.get("password"));

        // Teste 3: Password sem caracteres especiais
        globalUser.setPassword("Password1");
        violationMessages = ValidationUtil.validateAndGetViolations(validator, globalUser);
        assertEquals("Senha deve ter pelo menos uma letra maiúscula, uma letra minúscula, um número e um caractere especial, e deve ter pelo menos 8 caracteres.", violationMessages.get("password"));

        // Teste 4: Password com menos de 8 caracteres
        globalUser.setPassword("Pass1!");
        violationMessages = ValidationUtil.validateAndGetViolations(validator, globalUser);
        assertEquals("Senha deve ter pelo menos uma letra maiúscula, uma letra minúscula, um número e um caractere especial, e deve ter pelo menos 8 caracteres.", violationMessages.get("password"));
    }

    @Test //balance 
    public void testInvalidBalance() {
        // Teste 1: Balance inválido (null)
        globalUser.setBalance(null);
        Map<String, String>violationMessages = ValidationUtil.validateAndGetViolations(validator, globalUser);
        assertEquals("Balance não pode ser null", violationMessages.get("balance"));

        // Teste 2: Balance inválido (negativo)
        globalUser.setBalance(-50);
        violationMessages = ValidationUtil.validateAndGetViolations(validator, globalUser);
        assertEquals("Balance deve ser maior e igual à 0", violationMessages.get("balance"));
    }

    @Test //birthday 
    public void testInvalidBirthday() {
        // Teste: Data de nascimento no futuro
        globalUser.setBirthday(java.sql.Date.valueOf("2050-01-01"));
        Map<String, String>violationMessages = ValidationUtil.validateAndGetViolations(validator, globalUser);
        assertEquals("Data de nascimento tem que ser no passado", violationMessages.get("birthday"));
    }

    @Test  //streaks 
    public void testInvalidStreaks() {
        // Teste: Streaks é negativo
        globalUser.setStreaks(-5);
        Map<String, String> violationMessages = ValidationUtil.validateAndGetViolations(validator, globalUser);
        assertEquals("Streaks ter que ser maior e igual à 0", violationMessages.get("streaks"));
    }

    @Test  //semester 
    public void testInvalidSemester() {
        // Teste 1: Semestre é 0
        globalUser.setSemester(0);
        Map<String, String> violationMessages = ValidationUtil.validateAndGetViolations(validator, globalUser);
        assertEquals("Semestre deve ser positivo diferente de 0", violationMessages.get("semester"));

        // Teste 2: Semestre é negativo
        globalUser.setSemester(-1);
        violationMessages = ValidationUtil.validateAndGetViolations(validator, globalUser);
        assertEquals("Semestre deve ser positivo diferente de 0", violationMessages.get("semester"));
    }

}

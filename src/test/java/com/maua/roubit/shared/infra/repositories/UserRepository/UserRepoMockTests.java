package com.maua.roubit.shared.infra.repositories.UserRepository;

import com.maua.roubit.shared.domain.entities.Users;
import com.maua.roubit.shared.domain.enums.CoursesEnum;
import com.maua.roubit.shared.infra.repositories.UserRepoMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*; 
import java.sql.Date;


public class UserRepoMockTests {

    private UserRepoMock userRepository;
    private UUID userId1;
    private UUID userId2;
    private Users user1;
    private Users user2;

    @BeforeEach
    public void setUp() {
        userRepository = new UserRepoMock();

        userId1 = UUID.randomUUID();
        userId2 = UUID.randomUUID();

        user1 = new Users();
        user1.setUserId(userId1);
        user1.setUsername("john_doe");
        user1.setEmail("john.doe@example.com");
        user1.setName("John Doe");
        user1.setPassword("Password123!");
        user1.setBalance(100);
        user1.setBirthday(Date.valueOf("1990-01-01"));
        user1.setStreaks(10);
        user1.setSemester(5);
        user1.setCourse(CoursesEnum.CIENCIA_DA_COMPUTACAO);
        user1.setFriends(new HashSet<>());

        user2 = new Users();
        user2.setUserId(userId2);
        user2.setUsername("jane_doe");
        user2.setEmail("jane.doe@example.com");
        user2.setName("Jane Doe");
        user2.setPassword("Password123!");
        user2.setBalance(200);
        user2.setBirthday(Date.valueOf("1992-02-02"));
        user2.setStreaks(20);
        user2.setSemester(6);
        user2.setCourse(CoursesEnum.DESIGN);
        user2.setFriends(new HashSet<>());

        userRepository.save(user1);
        userRepository.save(user2);
    }

    @Test
    public void testFindByUsername() {
        Optional<Users> foundUser = userRepository.findByUsername("john_doe");
        Assertions.assertThat(foundUser).isPresent();
        Assertions.assertThat(foundUser.get().getEmail()).isEqualTo("john.doe@example.com");
    }

    @Test
    public void testFindByEmail() {
        Optional<Users> foundUser = userRepository.findByEmail("jane.doe@example.com");
        Assertions.assertThat(foundUser).isPresent();
        Assertions.assertThat(foundUser.get().getUsername()).isEqualTo("jane_doe");
    }

    @Test
    public void testExistsByEmail() {
        boolean exists = userRepository.existsByEmail("john.doe@example.com");
        Assertions.assertThat(exists).isTrue();
    }

    @Test
    public void testExistsByUsername() {
        boolean exists = userRepository.existsByUsername("jane_doe");
        Assertions.assertThat(exists).isTrue();
    }

    @Test
    public void testFindByNameContainingIgnoreCase() {
        var users = userRepository.findByNameContainingIgnoreCase("Jane");
        Assertions.assertThat(users).hasSize(1);
        Assertions.assertThat(users.get(0).getUsername()).isEqualTo("jane_doe");
    }

    @Test
    public void testFindByCourse() {
        var users = userRepository.findByCourse("CIENCIA_DA_COMPUTACAO");
        Assertions.assertThat(users).hasSize(1);
        Assertions.assertThat(users.get(0).getUsername()).isEqualTo("john_doe");
    }

    @Test
    public void testFindBySemester() {
        var users = userRepository.findBySemester(6);
        Assertions.assertThat(users).hasSize(1);
        Assertions.assertThat(users.get(0).getUsername()).isEqualTo("jane_doe");
    }

    @Test
    void testCountByCourse() {
        long count = userRepository.countByCourse(CoursesEnum.DESIGN);
        assertEquals(1, count);
    }

    @Test
    void testCountBySemester() {
        long count = userRepository.countBySemester(5);
        assertEquals(1, count);
    }

}

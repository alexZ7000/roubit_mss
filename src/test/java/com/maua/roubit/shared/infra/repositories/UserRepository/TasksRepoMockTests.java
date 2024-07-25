package com.maua.roubit.shared.infra.repositories.UserRepository;

import com.maua.roubit.shared.domain.entities.Tasks;
import com.maua.roubit.shared.domain.entities.Users;
import com.maua.roubit.shared.infra.repositories.TasksRepoMock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class TasksRepoMockTests {
    private TasksRepoMock tasksRepoMock;
    private Tasks task1;
    private Tasks task2;
    private Tasks task3;
    private Users user1;
    private Users user2;

    @BeforeEach
    public void setUp() {
        tasksRepoMock = new TasksRepoMock();

        user1 = new Users();
        user1.setUserId(UUID.randomUUID());

        user2 = new Users();
        user2.setUserId(UUID.randomUUID());

        task1 = new Tasks();
        task1.setTaskId(UUID.randomUUID());
        task1.setUserId(user1);
        task1.setName("Task1");
        task1.setDescription("Description1");
        task1.setImage("image1.png");
        task1.setChronometer(120L);
        task1.setTime(60L);

        task2 = new Tasks();
        task2.setTaskId(UUID.randomUUID());
        task2.setUserId(user1);
        task2.setName("Task2");
        task2.setDescription("Description2");
        task2.setImage("image2.png");
        task2.setChronometer(150L);
        task2.setTime(90L);

        task3 = new Tasks();
        task3.setTaskId(UUID.randomUUID());
        task3.setUserId(user2);
        task3.setName("Task3");
        task3.setDescription("Description3");
        task3.setImage("image3.png");
        task3.setChronometer(60L);
        task3.setTime(30L);

        tasksRepoMock.save(task1);
        tasksRepoMock.save(task2);
        tasksRepoMock.save(task3);
    }

    @Test
    public void testFindById() {
        Optional<Tasks> found = tasksRepoMock.findById(task1.getTaskId());
        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("Task1");
    }

    @Test
    public void testFindAll() {
        List<Tasks> found = tasksRepoMock.findAll();
        assertThat(found).hasSize(3);
    }

    @Test
    public void testDeleteById() {
        tasksRepoMock.deleteById(task1.getTaskId());
        Optional<Tasks> found = tasksRepoMock.findById(task1.getTaskId());
        assertThat(found).isEmpty();
    }

    @Test
    public void testFindByNameIgnoreCase() {
        Optional<Tasks> found = tasksRepoMock.findByNameIgnoreCase("task2");
        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("Task2");
    }

    @Test
    public void testFindByUserId() {
        List<Tasks> found = tasksRepoMock.findByUserId(user1);
        assertThat(found).hasSize(2);
    }

    @Test
    public void testFindByChronometerLessThanEqual() {
        List<Tasks> found = tasksRepoMock.findByChronometerLessThanEqual(120L);
        assertThat(found).hasSize(2);
    }

    @Test
    public void testFindByTimeBetween() {
        List<Tasks> found = tasksRepoMock.findByTimeBetween(30L, 90L);
        assertThat(found).hasSize(3);
    }

    @Test
    public void testCountByUserId() {
        long count = tasksRepoMock.countByUserId(user1);
        assertThat(count).isEqualTo(2);
    }
}

package com.maua.roubit.shared.domain.repositories;

import com.maua.roubit.shared.domain.entities.Tasks;
import com.maua.roubit.shared.domain.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TasksRepoInterface extends JpaRepository<Tasks, UUID> {
    
    // Encontra uma tarefa pelo nome, ignorando maiúsculas e minúsculas
    Optional<Tasks> findByNameIgnoreCase(String name);
    
    // Encontra todas as tarefas associadas a um usuário específico
    List<Tasks> findByUserId(Users userId);

    // Encontra todas as tarefas onde o cronômetro é menor ou igual a um valor dado
    List<Tasks> findByChronometerLessThanEqual(Long value);

    // Encontra todas as tarefas onde o tempo está entre dois valores
    List<Tasks> findByTimeBetween(Long startTime, Long endTime);
    
    // Conta o número de tarefas associadas a um usuário específico
    long countByUserId(Users userId);
}

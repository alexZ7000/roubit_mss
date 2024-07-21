package com.maua.roubit.shared.domain.repositories;

import com.maua.roubit.shared.domain.entities.Users;
import com.maua.roubit.shared.domain.enums.CoursesEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

 // JpaRepository oferece métodos padrão para operações de bd

public interface UserRepoInterface extends JpaRepository<Users, UUID> {

    // Métodos Adicionais Específicos

    // Encontrar usuário por nome de usuário
    Optional<Users> findByUsername(String username);

    // Encontrar usuário por e-mail
    Optional<Users> findByEmail(String email);

    // Verificar se um e-mail já está registrado
    boolean existsByEmail(String email);

    // Verificar se um nome de usuário já está registrado
    boolean existsByUsername(String username);

    // Encontrar usuários por nome (parcial ou completo)
    List<Users> findByNameContainingIgnoreCase(String name);

    // Encontrar todos os amigos de um usuário
    List<Users> findByFriends_UserId(UUID userId);

    // Encontrar todos os usuários por curso
    List<Users> findByCourse(String course);

    // Encontrar todos os usuários por semestre
    List<Users> findBySemester(Integer semester);

    // Contagem de usuários por curso e semestre
    long countByCourse(CoursesEnum course);
    long countBySemester(Integer semester);
}

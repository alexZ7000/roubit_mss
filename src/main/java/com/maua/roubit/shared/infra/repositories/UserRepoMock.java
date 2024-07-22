package com.maua.roubit.shared.infra.repositories;

import com.maua.roubit.shared.domain.entities.Users;
import com.maua.roubit.shared.domain.enums.CoursesEnum;
import com.maua.roubit.shared.domain.repositories.UserRepoInterface;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UserRepoMock implements UserRepoInterface {
    private final Map<UUID, Users> database = new HashMap<>();

    // Métodos específicos adicionados

    @Override
    public Optional<Users> findByUsername(String username) {
        return database.values().stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }

    @Override
    public Optional<Users> findByEmail(String email) {
        return database.values().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
    }

    @Override
    public boolean existsByEmail(String email) {
        return database.values().stream()
                .anyMatch(user -> user.getEmail().equals(email));
    }

    @Override
    public boolean existsByUsername(String username) {
        return database.values().stream()
                .anyMatch(user -> user.getUsername().equals(username));
    }

    @Override
    public List<Users> findByNameContainingIgnoreCase(String name) {
        return database.values().stream()
                .filter(user -> user.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Users> findByFriends_UserId(UUID userId) {
        return database.values().stream()
                .filter(user -> user.getFriends().stream().anyMatch(friend -> friend.getUserId().equals(userId)))
                .collect(Collectors.toList());
    }

    @Override
    public List<Users> findByCourse(String course) {
        return database.values().stream()
                .filter(user -> user.getCourse().toString().equals(course))
                .collect(Collectors.toList());
    }

    @Override
    public List<Users> findBySemester(Integer semester) {
        return database.values().stream()
                .filter(user -> user.getSemester().equals(semester))
                .collect(Collectors.toList());
    }

    @Override
    public long countByCourse(CoursesEnum course) {
        return database.values().stream()
                .filter(user -> user.getCourse().equals(course))
                .count();
    }

    @Override
    public long countBySemester(Integer semester) {
        return database.values().stream()
                .filter(user -> user.getSemester().equals(semester))
                .count();
    }

    // Métodos padrão do JpaRepository

    @Override
    public List<Users> findAll() {
        return new ArrayList<>(database.values());
    }

    @Override
    public Optional<Users> findById(UUID id) {
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public Users save(Users user) {
        if (user.getUserId() == null) {
            user.setUserId(UUID.randomUUID());
        }
        database.put(user.getUserId(), user);
        return user;
    }

    @Override
    public void deleteById(UUID id) {
        database.remove(id);
    }

    @Override
    public long count() {
        return database.size();
    }

    @Override
    public void delete(Users user) {
        if (user != null) {
            database.remove(user.getUserId());
        }
    }

    @Override
    public void deleteAll(Iterable<? extends Users> entities) {
        for (Users user : entities) {
            if (user != null) {
                database.remove(user.getUserId());
            }
        }
    }

    @Override
    public <S extends Users> List<S> saveAll(Iterable<S> entities) {
        List<S> result = new ArrayList<>();
        for (S entity : entities) {
            if (entity != null) {
                save(entity);
                result.add(entity);
            }
        }
        return result;
    }

    @Override
    public boolean existsById(UUID id) {
        return database.containsKey(id);
    }

    @Override
    public void deleteAllById(Iterable<? extends UUID> ids) {
        ids.forEach(database::remove);
    }

    //Métodos da interface JpaRepository que não foram implementados no mock, 
    //porque não são necessários para o propósito do mock ou são mais complexos.
    //Esses métodos lançam uma UnsupportedOperationException.

    @Override
    public <S extends Users> Optional<S> findOne(Example<S> example) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends Users> Page<S> findAll(Example<S> example, Pageable pageable) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends Users> long count(Example<S> example) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends Users> boolean exists(Example<S> example) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void flush() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends Users> S saveAndFlush(S entity) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends Users> List<S> saveAllAndFlush(Iterable<S> entities) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void deleteAllInBatch(Iterable<Users> entities) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<UUID> ids) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void deleteAllInBatch() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public Users getOne(UUID id) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public Users getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException("User not found with ID: " + id));
    }

    @Override
    public Users getReferenceById(UUID id) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends Users> List<S> findAll(Example<S> example) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends Users> List<S> findAll(Example<S> example, Sort sort) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public List<Users> findAllById(Iterable<UUID> ids) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public List<Users> findAll(Sort sort) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public Page<Users> findAll(Pageable pageable) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends Users, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not implemented");
    }
}

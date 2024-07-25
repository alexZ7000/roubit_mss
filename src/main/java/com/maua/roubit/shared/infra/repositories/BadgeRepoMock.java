package com.maua.roubit.shared.infra.repositories;

import com.maua.roubit.shared.domain.entities.Badge;
import com.maua.roubit.shared.domain.enums.RarityEnum;
import com.maua.roubit.shared.domain.repositories.BadgeRepoInterface;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BadgeRepoMock implements BadgeRepoInterface {
    private final Map<UUID, Badge> database = new HashMap<>();

    // Métodos específicos adicionados

    @Override
    public Optional<Badge> findByTitleIgnoreCase(String title) {
        return database.values().stream()
                .filter(badge -> badge.getTitle().equalsIgnoreCase(title))
                .findFirst();
    }

    @Override
    public List<Badge> findByRarity(RarityEnum rarity) {
        return database.values().stream()
                .filter(badge -> badge.getRarity().equals(rarity))
                .collect(Collectors.toList());
    }

    @Override
    public List<Badge> findByPriceLessThanEqual(Integer price) {
        return database.values().stream()
                .filter(badge -> badge.getPrice() <= price)
                .collect(Collectors.toList());
    }

    @Override
    public List<Badge> findByPriceBetween(Integer startPrice, Integer endPrice) {
        return database.values().stream()
                .filter(badge -> badge.getPrice() >= startPrice && badge.getPrice() <= endPrice)
                .collect(Collectors.toList());
    }

    @Override
    public List<Badge> findAllByOrderByPriceAsc() {
        return database.values().stream()
                .sorted(Comparator.comparingInt(Badge::getPrice))
                .collect(Collectors.toList());
    }

    @Override
    public List<Badge> findAllByOrderByPriceDesc() {
        return database.values().stream()
                .sorted((badge1, badge2) -> badge2.getPrice().compareTo(badge1.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public long countByRarity(RarityEnum rarity) {
        return database.values().stream()
                .filter(badge -> badge.getRarity().equals(rarity))
                .count();
    }

    // Métodos padrão do JpaRepository

    @Override
    public List<Badge> findAll() {
        return new ArrayList<>(database.values());
    }

    @Override
    public Optional<Badge> findById(UUID id) {
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public Badge save(Badge badge) {
        if (badge.getBadgeId() == null) {
            badge.setBadgeId(UUID.randomUUID());
        }
        database.put(badge.getBadgeId(), badge);
        return badge;
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
    public void delete(Badge badge) {
        if (badge != null) {
            database.remove(badge.getBadgeId());
        }
    }

    @Override
    public void deleteAll(Iterable<? extends Badge> entities) {
        for (Badge badge : entities) {
            if (badge != null) {
                database.remove(badge.getBadgeId());
            }
        }
    }

    @Override
    public <S extends Badge> List<S> saveAll(Iterable<S> entities) {
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

    // Métodos da interface JpaRepository que não foram implementados no mock, 
    // porque não são necessários para o propósito do mock ou são mais complexos.
    // Esses métodos lançam uma UnsupportedOperationException.

    @Override
    public <S extends Badge> Optional<S> findOne(Example<S> example) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends Badge> Page<S> findAll(Example<S> example, Pageable pageable) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends Badge> long count(Example<S> example) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends Badge> boolean exists(Example<S> example) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void flush() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends Badge> S saveAndFlush(S entity) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends Badge> List<S> saveAllAndFlush(Iterable<S> entities) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void deleteAllInBatch(Iterable<Badge> entities) {
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
    public Badge getOne(UUID id) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public Badge getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException("Badge not found with ID: " + id));
    }

    @Override
    public Badge getReferenceById(UUID id) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends Badge> List<S> findAll(Example<S> example) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends Badge> List<S> findAll(Example<S> example, Sort sort) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public List<Badge> findAllById(Iterable<UUID> ids) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public List<Badge> findAll(Sort sort) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public Page<Badge> findAll(Pageable pageable) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends Badge, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not implemented");
    }
}

package com.maua.roubit.shared.infra.repositories;

import com.maua.roubit.shared.domain.entities.Cosmetics;
import com.maua.roubit.shared.domain.enums.RarityEnum;
import com.maua.roubit.shared.domain.repositories.CosmeticsRepoInterface;

import org.springframework.data.domain.*;
import org.springframework.data.repository.query.FluentQuery;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CosmeticsRepoMock implements CosmeticsRepoInterface {
    private final Map<UUID, Cosmetics> database = new HashMap<>();

    // Métodos específicos adicionados

    @Override
    public Optional<Cosmetics> findByTitleIgnoreCase(String title) {
        return database.values().stream()
                .filter(cosmetic -> cosmetic.getTitle().equalsIgnoreCase(title))
                .findFirst();
    }

    @Override
    public List<Cosmetics> findByRarity(RarityEnum rarity) {
        return database.values().stream()
                .filter(cosmetic -> cosmetic.getRarity().equals(rarity))
                .collect(Collectors.toList());
    }

    @Override
    public List<Cosmetics> findByPriceLessThanEqual(Integer price) {
        return database.values().stream()
                .filter(cosmetic -> cosmetic.getPrice() <= price)
                .collect(Collectors.toList());
    }

    @Override
    public List<Cosmetics> findByPriceBetween(Integer startPrice, Integer endPrice) {
        return database.values().stream()
                .filter(cosmetic -> cosmetic.getPrice() >= startPrice && cosmetic.getPrice() <= endPrice)
                .collect(Collectors.toList());
    }

    @Override
    public List<Cosmetics> findAllByOrderByPriceAsc() {
        return database.values().stream()
                .sorted(Comparator.comparing(Cosmetics::getPrice))
                .collect(Collectors.toList());
    }

    @Override
    public List<Cosmetics> findAllByOrderByPriceDesc() {
        return database.values().stream()
                .sorted(Comparator.comparing(Cosmetics::getPrice).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public long countByRarity(RarityEnum rarity) {
        return database.values().stream()
                .filter(cosmetic -> cosmetic.getRarity().equals(rarity))
                .count();
    }

    // Métodos padrão do JpaRepository

    @Override
    public List<Cosmetics> findAll() {
        return new ArrayList<>(database.values());
    }

    @Override
    public Optional<Cosmetics> findById(UUID id) {
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public Cosmetics save(Cosmetics cosmetic) {
        if (cosmetic.getCosmeticId() == null) {
            cosmetic.setCosmeticId(UUID.randomUUID());
        }
        database.put(cosmetic.getCosmeticId(), cosmetic);
        return cosmetic;
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
    public void delete(Cosmetics cosmetic) {
        if (cosmetic != null) {
            database.remove(cosmetic.getCosmeticId());
        }
    }

    @Override
    public void deleteAll(Iterable<? extends Cosmetics> entities) {
        for (Cosmetics cosmetic : entities) {
            if (cosmetic != null) {
                database.remove(cosmetic.getCosmeticId());
            }
        }
    }

    @Override
    public <S extends Cosmetics> List<S> saveAll(Iterable<S> entities) {
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
    public <S extends Cosmetics> Optional<S> findOne(Example<S> example) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends Cosmetics> Page<S> findAll(Example<S> example, Pageable pageable) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends Cosmetics> long count(Example<S> example) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends Cosmetics> boolean exists(Example<S> example) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void flush() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends Cosmetics> S saveAndFlush(S entity) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends Cosmetics> List<S> saveAllAndFlush(Iterable<S> entities) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void deleteAllInBatch(Iterable<Cosmetics> entities) {
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
    public Cosmetics getOne(UUID id) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public Cosmetics getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException("Cosmetics not found with ID: " + id));
    }

    @Override
    public Cosmetics getReferenceById(UUID id) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends Cosmetics> List<S> findAll(Example<S> example) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends Cosmetics> List<S> findAll(Example<S> example, Sort sort) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public List<Cosmetics> findAllById(Iterable<UUID> ids) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public List<Cosmetics> findAll(Sort sort) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public Page<Cosmetics> findAll(Pageable pageable) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends Cosmetics, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not implemented");
    }
}

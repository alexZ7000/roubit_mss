package com.maua.roubit.shared.infra.repositories;

import com.maua.roubit.shared.domain.entities.FriendsRequests;
import com.maua.roubit.shared.domain.entities.Users;
import com.maua.roubit.shared.domain.enums.FriendsRequestsStatusEnum;
import com.maua.roubit.shared.domain.repositories.FriendRequestsRepoInterface;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

public class FriendRequestsRepoMock implements FriendRequestsRepoInterface {

    private final List<FriendsRequests> requests = new ArrayList<>();

    @Override
    public <S extends FriendsRequests> S save(S entity) {
        requests.removeIf(request -> request.getRequestId().equals(entity.getRequestId()));
        requests.add(entity);
        return entity;
    }

    @Override
    public Optional<FriendsRequests> findById(UUID requestId) {
        return requests.stream()
                .filter(request -> request.getRequestId().equals(requestId))
                .findFirst();
    }

    @Override
    public List<FriendsRequests> findAll() {
        return new ArrayList<>(requests);
    }

    @Override
    public List<FriendsRequests> findBySenderId(Users sender) {
        return requests.stream()
                .filter(request -> request.getSenderId().equals(sender))
                .collect(Collectors.toList());
    }

    @Override
    public List<FriendsRequests> findByReceiverId(Users receiver) {
        return requests.stream()
                .filter(request -> request.getReceiverId().equals(receiver))
                .collect(Collectors.toList());
    }

    @Override
    public List<FriendsRequests> findByStatus(FriendsRequestsStatusEnum status) {
        return requests.stream()
                .filter(request -> request.getStatus().equals(status))
                .collect(Collectors.toList());
    }

    @Override
    public long countByStatus(FriendsRequestsStatusEnum status) {
        return requests.stream()
                .filter(request -> request.getStatus().equals(status))
                .count();
    }

    @Override
    public List<FriendsRequests> findBySenderIdAndStatus(Users sender, FriendsRequestsStatusEnum status) {
        return requests.stream()
                .filter(request -> request.getSenderId().equals(sender) && request.getStatus().equals(status))
                .collect(Collectors.toList());
    }

    @Override
    public List<FriendsRequests> findByReceiverIdAndStatus(Users receiver, FriendsRequestsStatusEnum status) {
        return requests.stream()
                .filter(request -> request.getReceiverId().equals(receiver) && request.getStatus().equals(status))
                .collect(Collectors.toList());
    }

    @Override
    public List<FriendsRequests> findByRequestDate(Date requestDate) {
        return requests.stream()
                .filter(request -> request.getRequestDate().equals(requestDate))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(UUID requestId) {
        requests.removeIf(request -> request.getRequestId().equals(requestId));
    }

    @Override
    public void deleteAll() {
        requests.clear();
    }

    @Override
    public long count() {
        return requests.size();
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<UUID> ids) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAllByIdInBatch'");
    }

    @Override
    public void deleteAllInBatch() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAllInBatch'");
    }

    @Override
    public void deleteAllInBatch(Iterable<FriendsRequests> entities) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAllInBatch'");
    }

    @Override
    public <S extends FriendsRequests> List<S> findAll(Example<S> example) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public <S extends FriendsRequests> List<S> findAll(Example<S> example, Sort sort) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public void flush() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'flush'");
    }

    @Override
    public FriendsRequests getById(UUID arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public FriendsRequests getOne(UUID arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOne'");
    }

    @Override
    public FriendsRequests getReferenceById(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getReferenceById'");
    }

    @Override
    public <S extends FriendsRequests> List<S> saveAllAndFlush(Iterable<S> entities) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveAllAndFlush'");
    }

    @Override
    public <S extends FriendsRequests> S saveAndFlush(S entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveAndFlush'");
    }

    @Override
    public <S extends FriendsRequests> List<S> saveAll(Iterable<S> entities) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveAll'");
    }

    @Override
    public List<FriendsRequests> findAllById(Iterable<UUID> ids) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllById'");
    }

    @Override
    public boolean existsById(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'existsById'");
    }

    @Override
    public void delete(FriendsRequests entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public void deleteAllById(Iterable<? extends UUID> ids) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAllById'");
    }

    @Override
    public void deleteAll(Iterable<? extends FriendsRequests> entities) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAll'");
    }

    @Override
    public List<FriendsRequests> findAll(Sort sort) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Page<FriendsRequests> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public <S extends FriendsRequests> Optional<S> findOne(Example<S> example) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findOne'");
    }

    @Override
    public <S extends FriendsRequests> Page<S> findAll(Example<S> example, Pageable pageable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public <S extends FriendsRequests> long count(Example<S> example) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'count'");
    }

    @Override
    public <S extends FriendsRequests> boolean exists(Example<S> example) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'exists'");
    }

    @Override
    public <S extends FriendsRequests, R> R findBy(Example<S> example,
            Function<FetchableFluentQuery<S>, R> queryFunction) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findBy'");
    }
}

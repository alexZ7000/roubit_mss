package com.maua.roubit.shared.infra.repositories.UserRepository;

import com.maua.roubit.shared.domain.entities.FriendsRequests;
import com.maua.roubit.shared.domain.entities.Users;
import com.maua.roubit.shared.domain.enums.FriendsRequestsStatusEnum;
import com.maua.roubit.shared.infra.repositories.FriendRequestsRepoMock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class FriendRequestsRepoMockTests {

    private FriendRequestsRepoMock friendRequestsRepoMock;
    private Users user1;
    private Users user2;
    private FriendsRequests request1;
    private FriendsRequests request2;
    private FriendsRequests request3;

    @BeforeEach
    public void setUp() {
        friendRequestsRepoMock = new FriendRequestsRepoMock();

        user1 = new Users();
        user1.setUserId(UUID.randomUUID());

        user2 = new Users();
        user2.setUserId(UUID.randomUUID());

        request1 = new FriendsRequests();
        request1.setRequestId(UUID.randomUUID());
        request1.setSenderId(user1);
        request1.setReceiverId(user2);
        request1.setStatus(FriendsRequestsStatusEnum.PENDENTE);
        request1.setRequestDate(Date.valueOf("2024-01-01"));

        request2 = new FriendsRequests();
        request2.setRequestId(UUID.randomUUID());
        request2.setSenderId(user2);
        request2.setReceiverId(user1);
        request2.setStatus(FriendsRequestsStatusEnum.APROVADO);
        request2.setRequestDate(Date.valueOf("2024-02-01"));

        request3 = new FriendsRequests();
        request3.setRequestId(UUID.randomUUID());
        request3.setSenderId(user1);
        request3.setReceiverId(user2);
        request3.setStatus(FriendsRequestsStatusEnum.RECUSADO);
        request3.setRequestDate(Date.valueOf("2024-03-01"));

        friendRequestsRepoMock.save(request1);
        friendRequestsRepoMock.save(request2);
        friendRequestsRepoMock.save(request3);
    }

    @Test
    public void testFindById() {
        Optional<FriendsRequests> found = friendRequestsRepoMock.findById(request1.getRequestId());
        assertThat(found).isPresent();
        assertThat(found.get().getRequestId()).isEqualTo(request1.getRequestId());
    }

    @Test
    public void testFindBySenderId() {
        List<FriendsRequests> found = friendRequestsRepoMock.findBySenderId(user1);
        assertThat(found).hasSize(2);
        assertThat(found).extracting(FriendsRequests::getSenderId).containsOnly(user1);
    }

    @Test
    public void testFindByReceiverId() {
        List<FriendsRequests> found = friendRequestsRepoMock.findByReceiverId(user2);
        assertThat(found).hasSize(2);
        assertThat(found).extracting(FriendsRequests::getReceiverId).containsOnly(user2);
    }

    @Test
    public void testFindByStatus() {
        List<FriendsRequests> found = friendRequestsRepoMock.findByStatus(FriendsRequestsStatusEnum.PENDENTE);
        assertThat(found).hasSize(1);
        assertThat(found.get(0).getStatus()).isEqualTo(FriendsRequestsStatusEnum.PENDENTE);
    }

    @Test
    public void testCountByStatus() {
        long count = friendRequestsRepoMock.countByStatus(FriendsRequestsStatusEnum.APROVADO);
        assertThat(count).isEqualTo(1);
    }

    @Test
    public void testFindBySenderIdAndStatus() {
        List<FriendsRequests> found = friendRequestsRepoMock.findBySenderIdAndStatus(user1, FriendsRequestsStatusEnum.RECUSADO);
        assertThat(found).hasSize(1);
        assertThat(found.get(0).getStatus()).isEqualTo(FriendsRequestsStatusEnum.RECUSADO);
    }

    @Test
    public void testFindByReceiverIdAndStatus() {
        List<FriendsRequests> found = friendRequestsRepoMock.findByReceiverIdAndStatus(user2, FriendsRequestsStatusEnum.RECUSADO);
        assertThat(found).hasSize(1);
        assertThat(found.get(0).getStatus()).isEqualTo(FriendsRequestsStatusEnum.RECUSADO);
    }

    @Test
    public void testFindByRequestDate() {
        List<FriendsRequests> found = friendRequestsRepoMock.findByRequestDate(Date.valueOf("2024-02-01"));
        assertThat(found).hasSize(1);
        assertThat(found.get(0).getRequestDate()).isEqualTo(Date.valueOf("2024-02-01"));
    }

    @Test
    public void testDeleteById() {
        friendRequestsRepoMock.deleteById(request1.getRequestId());
        Optional<FriendsRequests> found = friendRequestsRepoMock.findById(request1.getRequestId());
        assertThat(found).isNotPresent();
    }

    @Test
    public void testDeleteAll() {
        friendRequestsRepoMock.deleteAll();
        assertThat(friendRequestsRepoMock.findAll()).isEmpty();
    }

    @Test
    public void testCount() {
        long count = friendRequestsRepoMock.count();
        assertThat(count).isEqualTo(3);
    }
}

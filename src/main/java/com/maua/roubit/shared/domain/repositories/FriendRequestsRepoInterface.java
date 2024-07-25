package com.maua.roubit.shared.domain.repositories;

import com.maua.roubit.shared.domain.entities.FriendsRequests;
import com.maua.roubit.shared.domain.entities.Users;
import com.maua.roubit.shared.domain.enums.FriendsRequestsStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FriendRequestsRepoInterface extends JpaRepository<FriendsRequests, UUID> {

    // Encontrar solicitação de amizade pelo ID
    Optional<FriendsRequests> findById(UUID requestId);

    // Encontrar todas as solicitações de amizade
    List<FriendsRequests> findAll();

    // Encontrar solicitações de amizade pelo remetente
    List<FriendsRequests> findBySenderId(Users sender);

    // Encontrar solicitações de amizade pelo destinatário
    List<FriendsRequests> findByReceiverId(Users receiver);

    // Encontrar solicitações de amizade pelo status
    List<FriendsRequests> findByStatus(FriendsRequestsStatusEnum status);

    // Contar o número de solicitações de amizade com um status específico
    long countByStatus(FriendsRequestsStatusEnum status);

    // Encontrar solicitações de amizade pelo remetente e status
    List<FriendsRequests> findBySenderIdAndStatus(Users sender, FriendsRequestsStatusEnum status);

    // Encontrar solicitações de amizade pelo destinatário e status
    List<FriendsRequests> findByReceiverIdAndStatus(Users receiver, FriendsRequestsStatusEnum status);

    // Encontrar solicitações de amizade pela data
    List<FriendsRequests> findByRequestDate(java.sql.Date requestDate);
}

package com.maua.roubit.shared.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "friends")
@Getter
@Setter
public class Friends {
    @EmbeddedId
    private FriendsId id;

    @ManyToOne
    @JoinColumn(name = "user1_id", nullable = false)
    private Users user1;

    @ManyToOne
    @JoinColumn(name = "user2_id", nullable = false)
    private Users user2;

    @ManyToOne
    @JoinColumn(name = "request_id", nullable = false)
    private FriendsRequests request;
}

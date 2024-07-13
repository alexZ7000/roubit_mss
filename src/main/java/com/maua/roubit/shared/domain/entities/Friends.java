package com.maua.roubit.shared.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "friends")
@Getter
@Setter
public class Friends {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "friend_id")
    private Long friendId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users userId;

    @ManyToOne
    @JoinColumn(name = "requests_id")
    private FriendsRequests requestId;
}

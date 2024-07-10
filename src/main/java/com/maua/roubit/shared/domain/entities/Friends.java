package com.maua.roubit.shared.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
public class Friends {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "friend_id")
    private Long friendId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser userId;

    @ManyToOne
    @JoinColumn(name = "request_id")
    private FriendRequests requestId;
}

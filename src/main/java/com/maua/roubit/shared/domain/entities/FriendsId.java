package com.maua.roubit.shared.domain.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class FriendsId {
    @Column(name = "user1_id", insertable=false, updatable=false)
    private UUID user1;

    @Column(name = "user2_id", insertable=false, updatable=false)
    private UUID user2;
}

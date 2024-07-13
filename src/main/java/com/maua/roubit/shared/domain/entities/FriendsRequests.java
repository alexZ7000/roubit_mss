package com.maua.roubit.shared.domain.entities; 

import com.maua.roubit.shared.domain.enums.FriendsRequestsStatusEnum;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "friend_requests")
@Getter
@Setter
public class FriendsRequests {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "request_id", nullable = false, unique = true)
    private UUID requestId;
    
    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private Users senderId;
    
    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private Users receiver;

    @Column(length = 8)
    private FriendsRequestsStatusEnum status;
    
    @Column(name = "request_date")
    private java.sql.Date requestDate;
}

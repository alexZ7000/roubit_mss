package com.maua.roubit.shared.domain.entities; 

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Getter
@Setter
public class FriendRequests {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "request_id", nullable = false, unique = true)
    private UUID requestId;
    
    @ManyToOne
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JoinColumn(name = "sender_id", nullable = false)
    private AppUser senderId;
    
    @ManyToOne
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JoinColumn(name = "receiver_id", nullable = false)
    private AppUser receiver;
    
    private String status;
    
    @Column(name = "request_date")
    private java.sql.Date requestDate;
}

package com.maua.roubit.shared.domain.entities; 

import com.maua.roubit.shared.domain.enums.FriendsRequestsStatusEnum;
import com.maua.roubit.shared.utils.validators.domain.entities.ValidateUUID;
import com.maua.roubit.shared.utils.validators.domain.enums.ValidateEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "friend_requests")
@Getter
@Setter
public class FriendsRequests {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "request_id", nullable = false, unique = true, length = 36)
    @NotNull(message = "Request ID não pode ser null")
    @ValidateUUID()
    private UUID requestId;
    
    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    @NotNull(message = "Sender ID não pode ser null")
    private Users senderId;
    
    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    @NotNull(message = "Receiver ID não pode ser null")
    private Users receiverId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Size(min = 8, max = 8, message = "Friend Status pode ter no máximo 8 caracteres")
    @NotBlank(message = "Friend Status não pode estar em branco")
    @ValidateEnum(enumClass = FriendsRequestsStatusEnum.class, message = "Friend Status de tipo inválido")
    private FriendsRequestsStatusEnum status = FriendsRequestsStatusEnum.PENDENTE;
    
    @Column(name = "request_date", nullable = false)
    @NotBlank(message = "Data de pedido de amizade não pode estar em branco")
    @PastOrPresent(message = "Data de pedido de amizade deve estar no presente ou no passado")
    private java.sql.Date requestDate;
}

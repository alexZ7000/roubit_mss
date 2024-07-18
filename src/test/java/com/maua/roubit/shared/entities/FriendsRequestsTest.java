package com.maua.roubit.shared.entities;

import static org.junit.jupiter.api.Assertions.*;

import com.maua.roubit.shared.domain.entities.FriendsRequests;
import com.maua.roubit.shared.domain.entities.Users;
import com.maua.roubit.shared.domain.enums.CoursesEnum;
import com.maua.roubit.shared.domain.enums.FriendsRequestsStatusEnum;
import com.maua.roubit.shared.utils.validators.errors.ValidationUtil;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.UUID;
import java.sql.Date;

public class FriendsRequestsTest {

    private Validator validator;
    private FriendsRequests friendsRequests;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        Users sender = new Users();
        sender.setUserId(UUID.randomUUID());
        sender.setProfilePicture("https://example.com/sender.png");
        sender.setName("Sender User");
        sender.setUsername("sender123");
        sender.setEmail("sender@example.com");
        sender.setPassword("SecurePassword1!");
        sender.setBalance(100);
        sender.setBirthday(Date.valueOf("1990-01-01"));
        sender.setStreaks(5);
        sender.setSemester(1);
        sender.setCourse(CoursesEnum.CIENCIA_DA_COMPUTACAO);

        Users receiver = new Users();
        receiver.setUserId(UUID.randomUUID());
        receiver.setProfilePicture("https://example.com/receiver.png");
        receiver.setName("Receiver User");
        receiver.setUsername("receiver123");
        receiver.setEmail("receiver@example.com");
        receiver.setPassword("SecurePassword2!");
        receiver.setBalance(200);
        receiver.setBirthday(Date.valueOf("1992-02-02"));
        receiver.setStreaks(10);
        receiver.setSemester(2);
        receiver.setCourse(CoursesEnum.CIENCIA_DA_COMPUTACAO);

        friendsRequests = new FriendsRequests();
        friendsRequests.setRequestId(UUID.randomUUID());
        friendsRequests.setSenderId(sender); 
        friendsRequests.setReceiverId(receiver); 
        friendsRequests.setStatus(FriendsRequestsStatusEnum.RECUSADO);
        friendsRequests.setRequestDate(new Date(System.currentTimeMillis()));
    }

    @Test
    public void testValidFriendRequest() {
        // Verifying the values set in the setup method
        assertNotNull(friendsRequests.getRequestId());
        assertNotNull(friendsRequests.getSenderId());
        assertNotNull(friendsRequests.getReceiverId());
        assertEquals(FriendsRequestsStatusEnum.RECUSADO, friendsRequests.getStatus());
        assertNotNull(friendsRequests.getRequestDate());

        // Additional checks for the sender and receiver
        assertEquals("Sender User", friendsRequests.getSenderId().getName());
        assertEquals("receiver123", friendsRequests.getReceiverId().getUsername());
    }

    @Test //requestId
    public void testBlankRequestId() {
        friendsRequests.setRequestId(null);
        Map<String, String> violationMessages = ValidationUtil.validateAndGetViolations(validator, friendsRequests);
        assertEquals("Request ID não pode ser null", violationMessages.get("requestId"));
    }

    @Test //senderId
    public void testNullSenderId() {
        friendsRequests.setSenderId(null);
        Map<String, String> violationMessages = ValidationUtil.validateAndGetViolations(validator, friendsRequests);
        assertEquals("Sender ID não pode ser null", violationMessages.get("senderId"));
    }

    @Test //receiverId
    public void testNullReceiverId() {
        friendsRequests.setReceiverId(null);
        Map<String, String> violationMessages = ValidationUtil.validateAndGetViolations(validator, friendsRequests);
        assertEquals("Receiver ID não pode ser null", violationMessages.get("receiverId"));
    }

    @Test //status 
    public void testInvalidStatus() {
        friendsRequests.setStatus(null);
        Map<String, String> violationMessages = ValidationUtil.validateAndGetViolations(validator, friendsRequests);
        assertEquals("Friend Status não pode estar em branco", violationMessages.get("status"));
    }

    @Test //requestDate
    public void testBlankRequestDate() {
        friendsRequests.setRequestDate(null);
        Map<String, String> violationMessages = ValidationUtil.validateAndGetViolations(validator, friendsRequests);
        assertEquals("Data de pedido de amizade não pode estar em branco", violationMessages.get("requestDate"));
    }
}

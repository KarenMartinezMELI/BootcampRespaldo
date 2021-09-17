package com.example.sendmessages.controller;

import com.example.sendmessages.dto.MessageSendedDTO;
import com.example.sendmessages.exception.EnableStateIncompatibleMsg;
import com.example.sendmessages.exception.CostNotReachedMsg;
import com.example.sendmessages.exception.MessengerNotFound;
import com.example.sendmessages.exception.NotPosibleSendMsg;
import com.example.sendmessages.service.IMessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMessagesController {

    private IMessageService service;

    public SendMessagesController(IMessageService service) {
        this.service = service;
    }

    @PutMapping("/messenger/{messengerId}/use")
    public void use(@PathVariable String messengerId) throws MessengerNotFound, EnableStateIncompatibleMsg {
        this.service.useMessenger(messengerId);
    }

    @PutMapping("/messenger/{messengerId}/stopUse")
    public void stopUse(@PathVariable String messengerId) throws MessengerNotFound, EnableStateIncompatibleMsg {
        this.service.stopUseMessenger(messengerId);
    }

    @GetMapping("/messenger/{messengerId}/sendMessage/{msg}/{km}/{money}")
    public ResponseEntity<MessageSendedDTO> sendMessage(
            @PathVariable String    messengerId,
            @PathVariable String    msg,
            @PathVariable int       km,
            @PathVariable int       money )
            throws NotPosibleSendMsg, MessengerNotFound {

        return ResponseEntity.ok(
                this.service.sendMessage(messengerId, msg, km, money) );
    }
}

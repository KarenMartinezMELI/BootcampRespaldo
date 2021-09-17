package com.example.sendmessages.service;


import com.example.sendmessages.dto.MessageSendedDTO;
import com.example.sendmessages.exception.EnableStateIncompatibleMsg;
import com.example.sendmessages.exception.MessengerNotFound;
import com.example.sendmessages.exception.NotPosibleSendMsg;
import com.example.sendmessages.model.*;
import com.example.sendmessages.repository.IMessengerRepository;
import org.springframework.stereotype.Service;

@Service
public class MessageService implements  IMessageService{

    IMessengerRepository repoMessenger;

    public MessageService(IMessengerRepository repoMessenger) {
        this.repoMessenger = repoMessenger;
    }

    public void useMessenger(String messengerId)
            throws MessengerNotFound, EnableStateIncompatibleMsg {

        Messenger messenger = this.repoMessenger.findById(messengerId);
        messenger.setEnable(true);
    }

    public void stopUseMessenger(String messengerId)
            throws MessengerNotFound, EnableStateIncompatibleMsg {
        Messenger messenger = this.repoMessenger.findById(messengerId);
        messenger.setEnable(false);
    }

    public MessageSendedDTO sendMessage(String messengerId, String msg, int km, int money)
            throws NotPosibleSendMsg, MessengerNotFound {

        Messenger messenger = this.repoMessenger.findById(messengerId);
        Message message = messenger.sendMessage(msg, km, money);

        return new MessageSendedDTO(
                message.getCost(),
                message.getBack(),
                message.getMessage(),
                messengerId,
                messenger.getType()
        );
    }



}


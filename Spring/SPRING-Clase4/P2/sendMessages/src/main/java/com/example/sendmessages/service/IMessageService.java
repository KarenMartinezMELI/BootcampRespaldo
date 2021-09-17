package com.example.sendmessages.service;

import com.example.sendmessages.dto.MessageSendedDTO;
import com.example.sendmessages.exception.EnableStateIncompatibleMsg;
import com.example.sendmessages.exception.MessengerNotFound;
import com.example.sendmessages.exception.NotPosibleSendMsg;

public interface IMessageService {

    void useMessenger(String messengerId)
            throws MessengerNotFound, EnableStateIncompatibleMsg;

    void stopUseMessenger(String messengerId)
            throws MessengerNotFound, EnableStateIncompatibleMsg;

    MessageSendedDTO sendMessage(String messengerId, String msg, int km, int money)
            throws NotPosibleSendMsg, MessengerNotFound;
}

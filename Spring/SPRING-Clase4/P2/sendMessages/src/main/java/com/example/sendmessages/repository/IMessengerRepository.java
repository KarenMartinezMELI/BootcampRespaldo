package com.example.sendmessages.repository;

import com.example.sendmessages.exception.MessengerNotFound;
import com.example.sendmessages.model.Messenger;

public interface IMessengerRepository {
    Messenger findById(String messengerId) throws MessengerNotFound;
}

package com.example.sendmessages.repository;

import com.example.sendmessages.exception.MessengerNotFound;
import com.example.sendmessages.model.CellPhone;
import com.example.sendmessages.model.Messenger;
import com.example.sendmessages.model.MessengerPigeon;
import com.example.sendmessages.model.Telegraph;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MessengerRepository implements IMessengerRepository{
    Map<String, Messenger> messengers  = new HashMap<>();


    public MessengerRepository() {
        messengers.put("PEPE.PALOMA", new MessengerPigeon());
        messengers.put("PABLO.PALOMA", new MessengerPigeon());
        messengers.put("MI.TELEGRAFO", new Telegraph());
        messengers.put("MI.CELLPHONE", new CellPhone());

    }

    @Override
    public Messenger findById(String messengerId) throws MessengerNotFound {
        if( ! this.messengers.containsKey(messengerId)) {
            throw new MessengerNotFound();
        }

        return this.messengers.get(messengerId);
    }
}

package com.example.sendmessages.model;

public class MessengerPigeon extends Messenger {

    @Override
    public String getType() {
        return "Paloma";
    }

    @Override
    public String particularMessage(String msg) {
        return "Grru Rru Gu (Me agarran a la pata un papelito) <<" + msg +">>";
    }

    @Override
    protected int costMessage(int km) {
        return 14 * km;
    }
}
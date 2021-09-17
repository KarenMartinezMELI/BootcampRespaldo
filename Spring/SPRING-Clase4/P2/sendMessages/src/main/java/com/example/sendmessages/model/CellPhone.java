package com.example.sendmessages.model;

public class CellPhone
        extends Messenger {

    protected int costMessage(int km) {
        return 5;
    }

    @Override
    public String getType() {
        return "Celular";
    }

    @Override
    public String particularMessage(String msg) {
        return "Enviando por WhatsApp <<" + msg + ">>";
    }

}

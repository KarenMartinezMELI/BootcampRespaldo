package com.example.sendmessages.model;

public class Telegraph
        extends Messenger {

    @Override
    public String getType() {
        return "Telegrafo";
    }

    @Override
    public String particularMessage(String msg) {

        return "Pip piripipip pip pip <<" + msg + ">>";
    }

    @Override
    protected int costMessage(int km) {
        return 2 * km;
    }
}

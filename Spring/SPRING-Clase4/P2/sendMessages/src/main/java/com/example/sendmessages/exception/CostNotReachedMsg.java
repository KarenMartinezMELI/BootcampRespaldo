package com.example.sendmessages.exception;

public class CostNotReachedMsg extends NotPosibleSendMsg {
    int nedded;

    public CostNotReachedMsg(int nedded) {
        this.nedded = nedded;
    }

    public int getNedded() {
        return nedded;
    }
}

package com.example.sendmessages.exception;

public class EnableStateIncompatibleMsg extends NotPosibleSendMsg {
    boolean busyState;

    public EnableStateIncompatibleMsg(boolean busyState) {
        this.busyState = busyState;
    }

    public boolean isBusyState() {
        return busyState;
    }
}

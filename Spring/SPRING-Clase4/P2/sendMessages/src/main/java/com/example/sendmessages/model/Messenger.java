package com.example.sendmessages.model;

import com.example.sendmessages.exception.EnableStateIncompatibleMsg;
import com.example.sendmessages.exception.CostNotReachedMsg;
import com.example.sendmessages.exception.NotPosibleSendMsg;

public abstract class Messenger {

    boolean enable = false;

    public void setEnable(boolean enable) throws EnableStateIncompatibleMsg {
        if ( this.enable == enable) {
            throw new EnableStateIncompatibleMsg( this.enable);
        }
        this.enable = enable;
    }

    /**
     *
     * @param msg
     * @param km
     * @param money
     * @return
     * @throws NotPosibleSendMsg
     */
    public Message sendMessage(String msg, int km, int money) throws NotPosibleSendMsg {
        if( ! this.enable) { //!FIXME ver este estado
            throw new EnableStateIncompatibleMsg( this.enable);
        }

        int cost = costMessage(km);
        if ( money < cost ) {
            throw new CostNotReachedMsg(cost);
        }

        return new Message(
                cost,
                money - cost,
                particularMessage( msg ));
    }

    protected abstract String particularMessage(String msg);

    protected abstract int costMessage(int km);

    public abstract String getType();

}
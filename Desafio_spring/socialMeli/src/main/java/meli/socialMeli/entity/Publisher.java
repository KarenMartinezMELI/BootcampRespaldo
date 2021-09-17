package meli.socialMeli.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Publisher {
    List<ISubscriber> subscribers;

    public Publisher(){
        subscribers=new ArrayList<>();
    }

    public boolean subscribe(ISubscriber subscriber){
        if(subscribers.stream().filter(s->s.equals(subscriber)).count()==0){
            subscribers.add(subscriber);
            return true;
        }
        return false;
    }
    public boolean unsubscribe(ISubscriber subscriber){
        if(subscribers.stream().filter(s->s.equals(subscriber)).count()>0) {
            subscribers.remove(subscriber);
            return true;
        }
        return false;
    }

    public List<ISubscriber> getSubscribers() {
        return subscribers;
    }

}

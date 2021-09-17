package clase4.ej.linkTracker.modelLogic.repository;

import clase4.ej.linkTracker.exception.ResponseExceptionNoExist;
import clase4.ej.linkTracker.modelLogic.model.Link;
import clase4.ej.linkTracker.modelLogic.util.Calculator;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class LinkRepositoryImp implements ILinkRepository{

    private HashMap<Integer, Link> entity;
    private Calculator calculator;

    LinkRepositoryImp(){
        loadRepository();
    }

    private void loadRepository(){
        entity=new HashMap<>();
        calculator=new Calculator();
    }

    @Override
    public Link findById(int id) {
        if(entity.containsKey(id)){
            return entity.get(id);
        }else{
            throw new ResponseExceptionNoExist("["+id+"]");
        }
    }

    @Override
    public Link findByUrl(String url) {
        return getLinks().stream().filter(x->x.getUrl().equals(url))
                .findFirst().orElseThrow(() -> new ResponseExceptionNoExist("["+url+"]"));
    }

    public List<Link> getLinks(){
        List<Link> links =new ArrayList<>();
        for(Map.Entry<Integer, Link> l : entity.entrySet()) {
            links.add(l.getValue());
        }
        return links;
    }

    @Override
    public boolean modifyById(int id,Link aLink) {
        if(entity.containsKey(id)){
            entity.put(id,aLink);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Link add(Link link) {
        Link aLink=new Link(calculator.getCount(),link.getUrl(),0,link.getPassword());
        entity.put(calculator.getCount(),aLink);
        calculator.increment();
        return aLink;
    }

    @Override
    public void remove(int id) {
        if(entity.containsKey(id)){
            entity.remove(id);
        }else{
            throw new ResponseExceptionNoExist("["+id+"]");
        }
    }
}

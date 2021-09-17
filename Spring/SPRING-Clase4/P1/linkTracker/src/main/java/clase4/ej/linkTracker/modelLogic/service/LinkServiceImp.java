package clase4.ej.linkTracker.modelLogic.service;


import clase4.ej.linkTracker.dto.*;
import clase4.ej.linkTracker.exception.ResponseExceptionNoExist;
import clase4.ej.linkTracker.exception.ResponseExceptionNoValidUrl;
import clase4.ej.linkTracker.exception.ResponseExceptionUrlAlreadyExist;
import clase4.ej.linkTracker.modelLogic.model.Link;
import clase4.ej.linkTracker.modelLogic.repository.ILinkRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Primary
public class LinkServiceImp implements ILinkService{

    private final ILinkRepository repository;

    public LinkServiceImp(ILinkRepository repository) {
        this.repository = repository;
    }

    public ILinkRepository getLinkRepository(){
        return this.repository;
    }

    public String getRedirect(int id) {
        Link aLink=getLinkRepository().findById(id);
        aLink.setMetric(aLink.getMetric()+1);
        getLinkRepository().modifyById(id,aLink);
        return aLink.getUrl()+"?password="+aLink.getPassword();
    }

    public LinkMetricDTO getLinkMetric(int id){
        return parseLinkToDTOMetric(getLinkRepository().findById(id));
    }

    public LinkResponseCreationDTO createLink(LinkInitialDTO initial) {
        if(validLink(initial.getUrl())) {
            try {
                getLinkRepository().findByUrl(initial.getUrl());
                throw new ResponseExceptionUrlAlreadyExist(initial.getUrl());
            }catch(ResponseExceptionNoExist ex){
                return parseLinkToDTO(getLinkRepository().add(new Link(0,initial.getUrl(),0,initial.getPassword())));
            }
        }else{
            throw new ResponseExceptionNoValidUrl(initial.getUrl());
        }
    }

    public void removeLink(int id){
        getLinkRepository().remove(id);
    }

    public List<LinkAllDTO> getLinks() {
        List<LinkAllDTO> metrics = new ArrayList<>();
        for(Link l: getLinkRepository().getLinks()){
            metrics.add(parseLinkToDTOAll(l));
        }
        return metrics;
    }

    private LinkResponseCreationDTO parseLinkToDTO(Link aLink){
        return new LinkResponseCreationDTO(aLink.getId(),aLink.getUrl(),aLink.getPassword());
    }

    private LinkMetricDTO parseLinkToDTOMetric(Link aLink){
        return new LinkMetricDTO(aLink.getId(),aLink.getUrl(),aLink.getMetric());
    }

    private LinkAllDTO parseLinkToDTOAll(Link aLink){
        return new LinkAllDTO(aLink.getId(),aLink.getUrl(),aLink.getPassword(),aLink.getMetric());
    }

    private boolean validLink(String url){
        String regex = "((http|https)://)(www.)?"
                + "[a-zA-Z0-9@:%._\\+~#?&//=]"
                + "{2,256}\\.[a-z]"
                + "{2,6}\\b([-a-zA-Z0-9@:%"
                + "._\\+~#?&//=]*)";
        Pattern p = Pattern.compile(regex);

        //string vacio
        if (url == null) {
            return false;
        }

        Matcher m = p.matcher(url);
        return m.matches();
    }
}

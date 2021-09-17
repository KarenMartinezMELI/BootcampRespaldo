package clase4.ej.linkTracker.modelLogic.repository;

import clase4.ej.linkTracker.modelLogic.model.Link;

import java.util.List;

public interface ILinkRepository {

    Link findById(int id);
    Link add(Link aLink);
    void remove(int id);
    boolean modifyById(int id,Link aLink);
    Link findByUrl(String url);
    List<Link> getLinks();

}

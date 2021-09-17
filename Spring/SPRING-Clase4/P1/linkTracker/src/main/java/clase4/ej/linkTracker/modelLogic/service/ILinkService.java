package clase4.ej.linkTracker.modelLogic.service;

import clase4.ej.linkTracker.dto.LinkAllDTO;
import clase4.ej.linkTracker.dto.LinkResponseCreationDTO;
import clase4.ej.linkTracker.dto.LinkInitialDTO;
import clase4.ej.linkTracker.dto.LinkMetricDTO;
import clase4.ej.linkTracker.modelLogic.repository.ILinkRepository;

import java.util.List;

public interface ILinkService {

    String getRedirect(int id);
    LinkResponseCreationDTO createLink(LinkInitialDTO initial);
    LinkMetricDTO getLinkMetric(int id);
    ILinkRepository getLinkRepository();
    void removeLink(int id);
    List<LinkAllDTO> getLinks();
}

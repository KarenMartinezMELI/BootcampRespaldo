package clase4.ej.linkTracker.controller;

import clase4.ej.linkTracker.dto.LinkResponseCreationDTO;
import clase4.ej.linkTracker.dto.LinkInitialDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import clase4.ej.linkTracker.modelLogic.service.ILinkService;

import java.net.URI;

@RestController
@RequestMapping()
public class LinkController {

    private final ILinkService linkService;

    public LinkController(ILinkService linkService){
        this.linkService=linkService;
    }

    @GetMapping(path="link/{linkId}")
    public ResponseEntity<Void> getUrl(@PathVariable int linkId){
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(linkService.getRedirect(linkId))).build();
    }

    @PostMapping(path="link")
    public ResponseEntity<?> createLink(@RequestBody LinkInitialDTO initial){
        return new ResponseEntity<>(linkService.createLink(initial), HttpStatus.OK);
    }

    @PostMapping(path="invalidate/{linkId}")
    public ResponseEntity<?> invalidateLink(@PathVariable int linkId){
        linkService.removeLink(linkId);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @GetMapping(path="metrics/{linkId}")
    public ResponseEntity<?> metrics(@PathVariable int linkId){
        return new ResponseEntity<>(linkService.getLinkMetric(linkId), HttpStatus.OK);
    }

    @GetMapping(path="links")
    public ResponseEntity<?> getLinksList(){
        return new ResponseEntity<>(linkService.getLinks(), HttpStatus.OK);
    }
}

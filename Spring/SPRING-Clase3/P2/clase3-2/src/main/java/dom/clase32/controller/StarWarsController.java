package dom.clase32.controller;

import dom.clase32.dto.ErrorResponseDTO;
import dom.clase32.service.IStarWarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("StarWars")
public class StarWarsController {

    @Autowired //no necesario si existe constructor
    private IStarWarsService starWarsService;

    public StarWarsController(IStarWarsService aStarWarService){
        this.starWarsService=aStarWarService;
    }

    @GetMapping(path="/Character/Search")
    public ResponseEntity<?> getCharacter(@RequestParam String name){
        return new ResponseEntity<>(starWarsService.returnCharacters(name), HttpStatus.OK);
    }

}
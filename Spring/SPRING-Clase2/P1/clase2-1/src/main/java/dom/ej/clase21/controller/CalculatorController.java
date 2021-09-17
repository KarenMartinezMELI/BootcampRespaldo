package dom.ej.clase21.controller;


import dom.ej.clase21.model.Calculator;
import dom.ej.clase21.model.Propierty;
import dom.ej.clase21.model.Room;
import dom.ej.clase21.model.RoomM2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CalculatorController {

    @PostMapping(path="calcm2")
    public ResponseEntity<String> calcm2(@RequestBody Propierty propierty){
        return new ResponseEntity<>("El resultado de m2 de la propiedad es de: "+Calculator.calcm2(propierty),HttpStatus.OK);
    }


    @PostMapping(path="value")
    public ResponseEntity<String> propiertyValue(@RequestBody Propierty propierty){
        return new ResponseEntity<>("El valor de la propiedad es de "+Calculator.propiertyValue(propierty)+" USD", HttpStatus.OK);
    }

    @PostMapping(path="biggerRoom")
    public ResponseEntity<Room> biggerRoom(@RequestBody Propierty propierty){
        try {
            return new  ResponseEntity<Room>(Calculator.biggerRoom(propierty), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Room>(new Room(), HttpStatus.OK);
        }
    }

    @PostMapping(path="calcm2All")
    public ResponseEntity<List<RoomM2>> m2PerRoom(@RequestBody Propierty propierty){
        try {
            return new  ResponseEntity<List<RoomM2>>(Calculator.m2PerRoom(propierty), HttpStatus.OK);
        } catch (Exception e) {
            return new  ResponseEntity<List<RoomM2>>(new ArrayList<>(), HttpStatus.OK);
        }
    }
}

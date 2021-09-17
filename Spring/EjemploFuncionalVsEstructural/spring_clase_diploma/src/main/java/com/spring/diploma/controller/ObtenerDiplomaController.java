package com.spring.diploma.controller;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.spring.diploma.model.AlumnoDTO;
import com.spring.diploma.model.AsignaturaDTO;
import com.spring.diploma.model.DiplomaDTO;
import com.spring.diploma.model.IteradorDTO;
import com.spring.diploma.util.Timer;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ObtenerDiplomaController {

    @PostMapping("/diploma/funcional")
    public ResponseEntity<?> obtenerDiplomaFuncional(@RequestBody AlumnoDTO alumnoDTO){
        Timer timer = new Timer();
        long start = timer.start();
        double promedio = alumnoDTO.getAsignaturas().stream().mapToDouble(x -> x.getNota() ).sum()/alumnoDTO.getAsignaturas().size();
        long stop = timer.stop();
        String str = "start: "+start+" "+"stop: "+stop+" "+"elapsedTime: "+ timer.elapsedTime() +" ";
        System.out.println( str );
        DiplomaDTO diplomaDTO;
        if (promedio >= 9){
            diplomaDTO = new DiplomaDTO(str,"Felicidades Aprobaste", promedio, alumnoDTO);
        }else {
            diplomaDTO = new DiplomaDTO(str, "Segui participando", promedio, alumnoDTO);
        }
        return new ResponseEntity<>(diplomaDTO, HttpStatus.OK);
    }

    @PostMapping("/diploma/estructural")
    public ResponseEntity<?> obtenerDiplomaEstructural(@RequestBody AlumnoDTO alumnoDTO){
        Timer timer = new Timer();
        long start = timer.start();
        double promedio = 0;
        for (AsignaturaDTO asignaturas: alumnoDTO.getAsignaturas()
             ) {
            promedio += asignaturas.getNota();
        }
        promedio /= alumnoDTO.getAsignaturas().size();
        long stop = timer.stop();
        String str = "start: "+start+" "+"stop: "+stop+" "+"elapsedTime: "+ timer.elapsedTime() +" ";
        System.out.println( str );
        DiplomaDTO diplomaDTO;
        if (promedio >= 9){
            diplomaDTO = new DiplomaDTO(str,"Felicidades Aprobaste", promedio, alumnoDTO);
        }else {
            diplomaDTO = new DiplomaDTO(str, "Segui participando", promedio, alumnoDTO);
        }
        return new ResponseEntity<>(diplomaDTO, HttpStatus.OK);
    }

    @PostMapping("/diploma/iterador")
    public ResponseEntity<?> obtenerDiplomaIterando(@RequestBody IteradorDTO iteradorDTO) throws UnirestException {
        //Creando alumno
        AlumnoDTO alumno = new AlumnoDTO();
        List<AsignaturaDTO> asignaturas = new ArrayList<>();
        for (int i = 0; i < iteradorDTO.getSize(); i++) {
            asignaturas.add( new AsignaturaDTO("col",9) );
            asignaturas.add( new AsignaturaDTO("arg",9) );
            asignaturas.add( new AsignaturaDTO("chi",9) );
            asignaturas.add( new AsignaturaDTO("mex",8) );
            asignaturas.add( new AsignaturaDTO("bra",7) );
        }

        alumno.setNombre("meli");
        alumno.setAsignaturas( asignaturas );

        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.post("http://127.0.0.1:8080/diploma/"+iteradorDTO.getIterador() )
                .header("Content-Type", "application/json")
                .body( alumno.toString() )
                .asString();
        return new ResponseEntity<>(response.getBody().toString(), HttpStatus.OK);
    }

}

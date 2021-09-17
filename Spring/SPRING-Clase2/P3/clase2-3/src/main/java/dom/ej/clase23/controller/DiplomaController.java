package dom.ej.clase23.controller;

import dom.ej.clase23.dto.ErrorResponseDTO;
import dom.ej.clase23.dto.RespuestaGenericaDTO;
import dom.ej.clase23.service.DiplomaService;
import dom.ej.clase23.dto.StudentDTO;
import dom.ej.clase23.service.IDiplomaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Diploma")
public class DiplomaController {

    IDiplomaService diplomaService = new DiplomaService();

    @PostMapping(path="/calc")
    @ResponseBody
    public ResponseEntity<RespuestaGenericaDTO> calcDiploma(@RequestBody StudentDTO student){
        try {
            return new ResponseEntity<>(diplomaService.getDiploma(student), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponseDTO(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
        }
    }

}
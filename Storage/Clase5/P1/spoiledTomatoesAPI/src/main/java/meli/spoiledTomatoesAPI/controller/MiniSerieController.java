package meli.spoiledTomatoesAPI.controller;

import meli.spoiledTomatoesAPI.dto.miniserie.MiniSerieDTO;
import meli.spoiledTomatoesAPI.dto.miniserie.MiniSerieRequestDTO;
import meli.spoiledTomatoesAPI.dto.miniserie.MiniSerieResponseDTO;
import meli.spoiledTomatoesAPI.model.MiniSerie;
import meli.spoiledTomatoesAPI.service.IMiniSerieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/miniseries")
public class MiniSerieController {

    IMiniSerieService miniSerieService;

    MiniSerieController(IMiniSerieService miniSerieService) {
        this.miniSerieService = miniSerieService;
    }

    @GetMapping("")
    public List<MiniSerieResponseDTO> getAllMiniSeries() {
        return miniSerieService.getAllMiniSeries();
    }

    @PostMapping()
    public MiniSerieResponseDTO createMiniSerie(@Valid @RequestBody MiniSerieRequestDTO miniSerieRequestDTO) {
        return miniSerieService.createMiniSerie(miniSerieRequestDTO);
    }

    @GetMapping("/{id}")
    public MiniSerieResponseDTO getMiniSerieById(@PathVariable(value = "id") Long miniSerieId) {
        return miniSerieService.getMiniSerieById(miniSerieId);
    }

    @PutMapping("/{id}")
    public MiniSerieResponseDTO updateMiniSerie(@PathVariable(value = "id") Long noteId, @Valid @RequestBody MiniSerieRequestDTO miniSerieDTO) {
        return miniSerieService.updateMiniSerie(noteId, miniSerieDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMiniSerie(@PathVariable(value = "id") Long noteId) {
        miniSerieService.deleteMiniSerie(noteId);
        return ResponseEntity.ok().build();
    }
}

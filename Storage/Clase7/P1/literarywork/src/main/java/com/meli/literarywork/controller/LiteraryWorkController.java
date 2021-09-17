package com.meli.literarywork.controller;

import com.meli.literarywork.dto.literarywork.LiteraryWorkRequestDTO;
import com.meli.literarywork.dto.literarywork.LiteraryWorkResponseDTO;
import com.meli.literarywork.dto.literarywork.LiteraryWorkResponseWOAuthorDTO;
import com.meli.literarywork.service.ILiteraryWorkService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/literaryworks")
public class LiteraryWorkController {
    ILiteraryWorkService literaryWorkService;

    LiteraryWorkController(ILiteraryWorkService literaryWorkService) {
        this.literaryWorkService = literaryWorkService;
    }

    @GetMapping("")
    public List<LiteraryWorkResponseDTO> getAllLiteraryWorks() {
        return literaryWorkService.getAllLiteraryWorks();
    }

    @PostMapping()
    public LiteraryWorkResponseDTO createLiteraryWork(@Valid @RequestBody LiteraryWorkRequestDTO literaryWorkRequestDTO) {
        return literaryWorkService.createLiteraryWork(literaryWorkRequestDTO);
    }

    @GetMapping("/{id}")
    public LiteraryWorkResponseDTO getLiteraryWorkById(@PathVariable(value = "id") Long literaryWorkId) {
        return literaryWorkService.getLiteraryWorkById(literaryWorkId);
    }

    @GetMapping("/authors/{id}")
    public List<LiteraryWorkResponseWOAuthorDTO> getNotesOfAuthorById(@PathVariable(value = "id") Long authorId) {
        return literaryWorkService.getLiteraryWorksOfAuthorById(authorId);
    }

    @DeleteMapping("/{id}")
    public void deleteLiteraryWork(@PathVariable(value = "id") Long literaryWorkId) {
        literaryWorkService.deleteLiteraryWorkById(literaryWorkId);
    }

    @PutMapping("/{id}")
    public LiteraryWorkResponseDTO modifyLiteraryWork(@Valid @RequestBody LiteraryWorkRequestDTO literaryWorkRequestDTO) {
        return literaryWorkService.modifyLiteraryWork(literaryWorkRequestDTO);
    }

}

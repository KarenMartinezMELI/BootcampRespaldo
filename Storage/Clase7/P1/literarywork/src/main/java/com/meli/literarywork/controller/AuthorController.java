package com.meli.literarywork.controller;

import com.meli.literarywork.dto.author.AuthorRequestDTO;
import com.meli.literarywork.dto.author.AuthorResponseDTO;
import com.meli.literarywork.service.IAuthorService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    IAuthorService authorService;

    AuthorController(IAuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("")
    public List<AuthorResponseDTO> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @PostMapping("")
    public AuthorResponseDTO createAuthor(@Valid @RequestBody AuthorRequestDTO authorRequestDTO) {
        return authorService.createAuthor(authorRequestDTO);
    }

    @GetMapping("/{id}")
    public AuthorResponseDTO getAuthorById(@PathVariable(value = "id") Long authorId) {
        return authorService.getAuthorById(authorId);
    }

}

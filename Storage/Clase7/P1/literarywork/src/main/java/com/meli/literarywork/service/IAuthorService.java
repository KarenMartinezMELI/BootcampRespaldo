package com.meli.literarywork.service;

import com.meli.literarywork.domain.Author;
import com.meli.literarywork.dto.author.AuthorRequestDTO;
import com.meli.literarywork.dto.author.AuthorResponseDTO;
import com.meli.literarywork.exception.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public interface IAuthorService {

    List<AuthorResponseDTO> getAllAuthors();
    AuthorResponseDTO createAuthor(AuthorRequestDTO authorRequestDTO);
    AuthorResponseDTO getAuthorById(Long authorId);

}

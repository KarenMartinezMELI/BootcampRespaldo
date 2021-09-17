package com.meli.literarywork.service;

import com.meli.literarywork.domain.Author;

import com.meli.literarywork.dto.author.AuthorRequestDTO;
import com.meli.literarywork.dto.author.AuthorResponseDTO;
import com.meli.literarywork.exception.ResourceNotFoundException;
import com.meli.literarywork.repository.IAuthorRepository;
import com.meli.literarywork.utils.ListMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class AuthorServiceImp implements IAuthorService{
    IAuthorRepository authorRepository;
    ModelMapper modelMapper;
    ListMapper listMapper;


    AuthorServiceImp(IAuthorRepository authorRepository,
                           ModelMapper modelMapper,
                           ListMapper listMapper) {
        this.authorRepository = authorRepository;
        this.listMapper = listMapper;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<AuthorResponseDTO> getAllAuthors() {
        List<Author> list = new ArrayList<>();
        Iterator<Author> iterator=authorRepository.findAll().iterator();
        iterator.forEachRemaining(list::add);
        return listMapper.mapList(authorRepository.findAll(), AuthorResponseDTO.class);
    }

    @Override
    public AuthorResponseDTO createAuthor(AuthorRequestDTO authorRequestDTO) {
        Author author = modelMapper.map(authorRequestDTO, Author.class);
        AuthorResponseDTO resp =  modelMapper.map(authorRepository.save(author), AuthorResponseDTO.class);
        return resp;
    }

    @Override
    public AuthorResponseDTO getAuthorById(Long authorId) {
        Author note = authorRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Author", "id", authorId));
        return modelMapper.map(note, AuthorResponseDTO.class);
    }

}

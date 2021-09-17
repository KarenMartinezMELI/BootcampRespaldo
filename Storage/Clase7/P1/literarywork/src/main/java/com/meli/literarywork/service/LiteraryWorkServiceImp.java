package com.meli.literarywork.service;

import com.meli.literarywork.domain.Author;
import com.meli.literarywork.domain.LiteraryWork;
import com.meli.literarywork.dto.author.AuthorResponseDTO;
import com.meli.literarywork.dto.literarywork.LiteraryWorkRequestDTO;
import com.meli.literarywork.dto.literarywork.LiteraryWorkResponseDTO;
import com.meli.literarywork.dto.literarywork.LiteraryWorkResponseWOAuthorDTO;
import com.meli.literarywork.exception.ResourceNotFoundException;
import com.meli.literarywork.repository.IAuthorRepository;
import com.meli.literarywork.repository.ILiteraryWorkRepository;
import com.meli.literarywork.utils.ListMapper;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LiteraryWorkServiceImp implements ILiteraryWorkService {

    ILiteraryWorkRepository literaryWorkRepository;
    IAuthorRepository authorRepository;
    ModelMapper modelMapper;
    ListMapper listMapper;

    LiteraryWorkServiceImp(ILiteraryWorkRepository literaryWorkRepository,
                           IAuthorRepository authorRepository,
                           ModelMapper modelMapper,
                           ListMapper listMapper) {
        this.literaryWorkRepository = literaryWorkRepository;
        this.authorRepository = authorRepository;
        this.listMapper = listMapper;

        Converter<Long, Author> authorIdToAuthorConverter = new AbstractConverter<Long, Author>() {
            @Override
            protected Author convert(Long authorId) throws ResourceNotFoundException {
                return authorRepository.findById(authorId)
                        .orElseThrow(() -> new ResourceNotFoundException("Author", "id", authorId));
            }
        };

        modelMapper.typeMap(LiteraryWorkRequestDTO.class, LiteraryWork.class).addMappings( (mapper) ->
                mapper.using(authorIdToAuthorConverter)
                        .map(LiteraryWorkRequestDTO::getAuthorId, LiteraryWork::setAuthor)
        );

        this.modelMapper = modelMapper;
    }

    @Override
    public List<LiteraryWorkResponseDTO> getAllLiteraryWorks() {
        List<LiteraryWork> list = new ArrayList<>();
        Iterator<LiteraryWork> iterator=literaryWorkRepository.findAll().iterator();
        iterator.forEachRemaining(list::add);
        return listMapper.mapList(list, LiteraryWorkResponseDTO.class);
    }

    @Override
    public LiteraryWorkResponseDTO createLiteraryWork(LiteraryWorkRequestDTO literaryWorkRequestDTO) {
        LiteraryWork literaryWork = modelMapper.map(literaryWorkRequestDTO, LiteraryWork.class);
        LiteraryWorkResponseDTO resp =  modelMapper.map(literaryWorkRepository.save(literaryWork), LiteraryWorkResponseDTO.class);
        return resp;
    }

    @Override
    public LiteraryWorkResponseDTO getLiteraryWorkById(Long literaryWorkId) {
        LiteraryWork note = literaryWorkRepository.findById(literaryWorkId)
                .orElseThrow(() -> new ResourceNotFoundException("LiteraryWork", "id", literaryWorkId));
        return modelMapper.map(note, LiteraryWorkResponseDTO.class);
    }

    public List<LiteraryWorkResponseWOAuthorDTO> getLiteraryWorksOfAuthorById(Long authorId) {
        return listMapper.mapList(literaryWorkRepository.getLiteraryWorksOfAuthorById(authorId), LiteraryWorkResponseWOAuthorDTO.class);
    }

    @Override
    public LiteraryWorkResponseDTO modifyLiteraryWork(LiteraryWorkRequestDTO literaryWorkRequestDTO) {
        return modelMapper.map(literaryWorkRepository.save(modelMapper.map(literaryWorkRequestDTO, LiteraryWork.class)),LiteraryWorkResponseDTO.class);
    }

    @Override
    public void deleteLiteraryWorkById(Long literaryWorkId) {
        literaryWorkRepository.deleteById(literaryWorkId);
    }

}



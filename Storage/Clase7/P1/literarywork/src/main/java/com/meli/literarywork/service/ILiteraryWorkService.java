package com.meli.literarywork.service;

import com.meli.literarywork.dto.author.AuthorResponseDTO;
import com.meli.literarywork.dto.literarywork.LiteraryWorkRequestDTO;
import com.meli.literarywork.dto.literarywork.LiteraryWorkResponseDTO;
import com.meli.literarywork.dto.literarywork.LiteraryWorkResponseWOAuthorDTO;

import java.util.List;

public interface ILiteraryWorkService {

    List<LiteraryWorkResponseDTO> getAllLiteraryWorks();
    LiteraryWorkResponseDTO createLiteraryWork(LiteraryWorkRequestDTO literaryWorkRequestDTO);
    LiteraryWorkResponseDTO getLiteraryWorkById(Long literaryWorkId);
    List<LiteraryWorkResponseWOAuthorDTO> getLiteraryWorksOfAuthorById(Long authorId);

    LiteraryWorkResponseDTO modifyLiteraryWork(LiteraryWorkRequestDTO literaryWork);

    void deleteLiteraryWorkById(Long literaryWorkId);
}

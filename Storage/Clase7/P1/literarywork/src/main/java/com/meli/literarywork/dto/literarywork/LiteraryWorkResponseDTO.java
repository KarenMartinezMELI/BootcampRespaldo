package com.meli.literarywork.dto.literarywork;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.meli.literarywork.dto.author.AuthorDTO;
import com.meli.literarywork.dto.author.AuthorResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
// Used when extends from other DTO
// link: https://stackoverflow.com/questions/38572566/warning-equals-hashcode-on-data-annotation-lombok-with-inheritance
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
public class LiteraryWorkResponseDTO extends LiteraryWorkDTO {
    private Long id;
    AuthorResponseDTO author;
}

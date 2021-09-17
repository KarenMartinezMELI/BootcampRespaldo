package com.meli.literarywork.dto.literarywork;

import com.meli.literarywork.dto.author.AuthorDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
// Used when extends from other DTO
// link: https://stackoverflow.com/questions/38572566/warning-equals-hashcode-on-data-annotation-lombok-with-inheritance
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
public class LiteraryWorkResponseWOAuthorDTO extends LiteraryWorkDTO {
    private Long id;
}

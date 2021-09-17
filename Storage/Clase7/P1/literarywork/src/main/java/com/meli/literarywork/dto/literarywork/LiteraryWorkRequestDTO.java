package com.meli.literarywork.dto.literarywork;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
// Used when extends from other DTO
// link: https://stackoverflow.com/questions/38572566/warning-equals-hashcode-on-data-annotation-lombok-with-inheritance
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
public class LiteraryWorkRequestDTO extends LiteraryWorkDTO {
    @NotNull
    @JsonProperty("author_id")
    private Long authorId;
    @NotNull
    private Long id;
}

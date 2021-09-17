package com.meli.literarywork.dto.author;

import lombok.*;

import javax.validation.constraints.NotEmpty;


@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDTO {

    @NotEmpty
    private String name;
    @NotEmpty
    private String surname;

}

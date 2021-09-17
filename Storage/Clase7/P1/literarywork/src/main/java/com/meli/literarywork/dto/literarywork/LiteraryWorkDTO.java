package com.meli.literarywork.dto.literarywork;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Field;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LiteraryWorkDTO {

    @NotEmpty
    private String title;
    @NotNull
    Long pages;
    String editorial;
    @JsonProperty("first_publish_year")
    Long firstPublishYear;

}

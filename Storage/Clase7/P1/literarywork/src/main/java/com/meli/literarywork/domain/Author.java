package com.meli.literarywork.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Document(indexName="author")
public class Author {

    @Id
    private Long id;

    private String name;
    private String surname;
}

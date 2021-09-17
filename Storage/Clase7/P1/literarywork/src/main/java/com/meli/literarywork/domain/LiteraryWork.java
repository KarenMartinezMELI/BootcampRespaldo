package com.meli.literarywork.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
@Document(indexName="literary_work")
public class LiteraryWork {
    @Id
    private Long id;
    private Long pages;
    private String editorial;
    private String title;

    @Field(name="first_publish_year")
    private Long firstPublishYear;

    @Field(type= FieldType.Nested, includeInParent=true)
    private Author author;
}

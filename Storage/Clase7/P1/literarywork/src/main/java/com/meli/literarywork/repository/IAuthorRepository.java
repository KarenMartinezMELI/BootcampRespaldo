package com.meli.literarywork.repository;

import com.meli.literarywork.domain.Author;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAuthorRepository extends ElasticsearchRepository<Author,Long> {
@Query("GET /blog/_search\n" +
        "{\n" +
        "  'query': {\n" +
        "    'match_all': {}\n" +
        "  }\n" +
        "}")
    List<Author> findAll();


}

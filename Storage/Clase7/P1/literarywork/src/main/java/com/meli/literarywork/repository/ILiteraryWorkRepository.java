package com.meli.literarywork.repository;

import com.meli.literarywork.domain.LiteraryWork;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILiteraryWorkRepository extends ElasticsearchRepository<LiteraryWork,Long> {
    @Query("  {\n" +
            "    \"nested\": {\n" +
            "      \"path\": \"author\",\n" +
            "      \"query\": {\n" +
            "        \"bool\": {\n" +
            "          \"must\": [\n" +
            "            { \"match\": { \"author.id\": ?0 } }\n" +
            "          ]\n" +
            "        }\n" +
            "      }\n" +
            "    }\n" +
            "  }")
    List<LiteraryWork> getLiteraryWorksOfAuthorById(Long authorId);
}

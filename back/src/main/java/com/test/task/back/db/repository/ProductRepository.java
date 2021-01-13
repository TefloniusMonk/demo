package com.test.task.back.db.repository;

import com.test.task.back.db.document.Product;
import org.elasticsearch.common.Nullable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ProductRepository extends ElasticsearchRepository<Product, Long> {
    List<Product> findAll();

    @Query("{\"bool\" : {" +
            "            \"must\" : [" +
            "                { \"query_string\" : { \"query\" : \"*?1*\", \"fields\" : [ \"?0\" ], \"analyze_wildcard\": true } }" +
            "               " +
            "            ]" +
            "        }}")
    List<Product> searchByField(String field, String value);

}

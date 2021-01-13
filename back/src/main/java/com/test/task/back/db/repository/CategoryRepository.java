package com.test.task.back.db.repository;

import com.test.task.back.db.document.Category;
import org.elasticsearch.repositories.Repository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface CategoryRepository extends ElasticsearchRepository<Category, Long> {
    List<Category> findAll();
}

package com.test.task.back.service.api;

import com.test.task.back.db.document.Category;
import com.test.task.back.db.document.Product;
import org.elasticsearch.common.Nullable;

import java.util.List;
import java.util.Map;

public interface ProductService {
    Iterable<Product> save(Iterable<Product> products);

    List<Product> findByCriterias (String searchField, String searchValue, @Nullable Long categoryId);
}

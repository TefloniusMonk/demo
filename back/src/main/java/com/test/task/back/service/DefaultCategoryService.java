package com.test.task.back.service;

import com.test.task.back.db.document.Category;
import com.test.task.back.db.repository.CategoryRepository;
import com.test.task.back.service.api.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DefaultCategoryService implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Iterable<Category> save(Iterable<Category> categories) {
        return categoryRepository.saveAll(categories);
    }
}

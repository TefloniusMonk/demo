package com.test.task.back.service.api;

import com.test.task.back.db.document.Category;

import java.util.List;

public interface CategoryService {
    Iterable<Category> save(Iterable<Category> categories);
}

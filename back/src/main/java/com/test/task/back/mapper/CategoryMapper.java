package com.test.task.back.mapper;

import com.test.task.back.db.document.Category;
import com.test.task.back.dto.CategoryForm;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CategoryMapper implements Mapper<Category, CategoryForm> {
    private final ModelMapper mapper;

    @Override
    public List<Category> fromForms(List<CategoryForm> forms) {
        return forms.stream()
                .map(this::fromForm)
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryForm> toForms(List<Category> entities) {
        return entities.stream()
                .map(this::toForm)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryForm toForm(Category entity) {
        return mapper.map(entity, CategoryForm.class);
    }

    @Override
    public Category fromForm(CategoryForm form) {
        return mapper.map(form, Category.class);
    }
}

package com.test.task.back.dto;

import lombok.Data;

@Data
public class CategoryForm {
    private Long id;
    private Long parentId;
    private String description;

    public CategoryForm(Long id) {
        this.id = id;
    }

    public CategoryForm() {
    }
}

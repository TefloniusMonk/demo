package com.test.task.back.dto;

import lombok.Data;

@Data
public class ProductForm {
    private Long id;
    private Long categoryId;
    private String name;
    private String description;
    private Long price;
    private String pictureUrl;

    public ProductForm(Long id) {
        this.id = id;
    }

    public ProductForm() {
    }
}

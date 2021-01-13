package com.test.task.back.dto;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class CatalogForm {
    private List<CategoryForm> categories;
    private List<ProductForm> products;

    public CatalogForm() {
        categories = new LinkedList<>();
        products = new LinkedList<>();
    }
}

package com.test.task.back.mapper;

import com.test.task.back.db.document.Category;
import com.test.task.back.db.document.Product;
import com.test.task.back.dto.CategoryForm;
import com.test.task.back.dto.ProductForm;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ProductMapper implements Mapper<Product, ProductForm> {
    private final ModelMapper mapper;

    @Override
    public List<Product> fromForms(List<ProductForm> forms) {
        return forms.stream()
                .map(this::fromForm)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductForm> toForms(List<Product> entities) {
        return entities.stream()
                .map(this::toForm)
                .collect(Collectors.toList());
    }

    @Override
    public ProductForm toForm(Product entity) {
        return mapper.map(entity, ProductForm.class);
    }

    @Override
    public Product fromForm(ProductForm form) {
        return mapper.map(form, Product.class);
    }
}

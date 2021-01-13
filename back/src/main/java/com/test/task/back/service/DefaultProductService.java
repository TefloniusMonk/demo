package com.test.task.back.service;

import com.test.task.back.db.document.Product;
import com.test.task.back.db.repository.ProductRepository;
import com.test.task.back.service.api.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DefaultProductService implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Iterable<Product> save(Iterable<Product> products) {
        return productRepository.saveAll(products);
    }

    @Override
    public List<Product> findByCriterias(String searchField, String searchValue, Long categoryId) {
        List<Product> result = productRepository.searchByField(searchField, searchValue);
        if (categoryId != null) {
            return result.stream()
                    .filter(product -> product.getCategoryId().equals(categoryId))
                    .collect(Collectors.toList());
        }
        return result;
    }
}

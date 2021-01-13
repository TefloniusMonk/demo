package com.test.task.back.service;

import com.test.task.back.dataload.api.DataLoader;
import com.test.task.back.db.document.Category;
import com.test.task.back.db.document.Product;
import com.test.task.back.db.repository.CategoryRepository;
import com.test.task.back.db.repository.ProductRepository;
import com.test.task.back.dto.CatalogForm;
import com.test.task.back.service.api.DataLoadService;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class DataLoadServiceTest {
    @Autowired
    private DataLoadService dataLoadService;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void before() {
        categoryRepository.deleteAll();
        productRepository.deleteAll();
    }

    @Test
    public void shouldParseAndSave() {
        dataLoadService.saveFromUrl("http://frontend.tanuki.ru/feeds/raiden-delivery-club/");
        val categories = (List<Category>) categoryRepository.findAll();
        val products = (List<Product>) productRepository.findAll();
        assertNotNull(categories);
        assertNotNull(products);
        assertEquals(59, categories.size());
        assertEquals(337, products.size());
    }
}

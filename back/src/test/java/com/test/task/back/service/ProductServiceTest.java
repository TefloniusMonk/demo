package com.test.task.back.service;

import com.test.task.back.db.repository.ProductRepository;
import com.test.task.back.service.api.ProductService;
import com.test.task.back.testdata.TestData;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ProductServiceTest {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void before() {
        productRepository.deleteAll();
    }

    @Test
    public void shouldSave() {
        productService.save(Arrays.asList(TestData.newProduct()));
        val products = productRepository.findAll();
        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals(1L, products.get(0).getId());
        assertEquals("description", products.get(0).getDescription());
        assertEquals("name", products.get(0).getName());
        assertEquals("url", products.get(0).getPictureUrl());
        assertEquals(2L, products.get(0).getCategoryId());
        assertEquals(123L, products.get(0).getPrice());
    }

    @Test
    public void shouldUpdate() {
        productService.save(Arrays.asList(TestData.newProduct()));
        productService.save(Arrays.asList(TestData.newProduct2()));
        val products = productRepository.findAll();
        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals(1L, products.get(0).getId());
        assertEquals("new desc", products.get(0).getDescription());
        assertEquals("new name", products.get(0).getName());
        assertEquals("url2", products.get(0).getPictureUrl());
        assertEquals(3L, products.get(0).getCategoryId());
        assertEquals(1234L, products.get(0).getPrice());
    }

    @Test
    public void shouldSearchByName() {
        productRepository.saveAll(TestData.newProducts());

        val result = productService.findByCriterias("name", "Сэнсэй", null);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1496L, result.get(0).getId());
    }

    @Test
    public void shouldSearchByNotFullName() {
        productRepository.saveAll(TestData.newProducts());

        val result = productService.findByCriterias("name", "Сэнсэ", null);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1496L, result.get(0).getId());
    }
    @Test
    public void shouldSearchByNotFullDescription() {
        productRepository.saveAll(TestData.newProducts());

        val result = productService.findByCriterias("description", "Кревет", null);
        assertNotNull(result);
        assertEquals(3, result.size());
    }

    @Test
    public void shouldSearchByDescription() {
        productRepository.saveAll(TestData.newProducts());

        val result = productService.findByCriterias("description", "Креветка", null);
        assertNotNull(result);
        assertEquals(3, result.size());
    }

    @Test
    public void shouldSearchThenFilterByCategoryId() {
        productRepository.saveAll(TestData.newProducts());

        val result = productService.findByCriterias("name", "Ямато", 10L);
        assertNotNull(result);
        assertEquals(1, result.size());
    }
}

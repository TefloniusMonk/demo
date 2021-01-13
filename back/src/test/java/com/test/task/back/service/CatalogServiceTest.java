package com.test.task.back.service;

import com.test.task.back.db.repository.CategoryRepository;
import com.test.task.back.service.api.CategoryService;
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
public class CatalogServiceTest {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryService categoryService;

    @BeforeEach
    void before() {
        categoryRepository.deleteAll();
    }

    @Test
    public void shouldSave(){
            categoryService.save(Arrays.asList(TestData.newCategory()));
            val categories = categoryRepository.findAll();
            assertNotNull(categories);
            assertEquals(1, categories.size());
            assertEquals(1L, categories.get(0).getId());
            assertEquals("desc", categories.get(0).getDescription());
            assertEquals(2L, categories.get(0).getParentId());
    }

    @Test
    public void shouldUpdate(){
            categoryService.save(Arrays.asList(TestData.newCategory()));
            categoryService.save(Arrays.asList(TestData.newCategory2()));
            val categories = categoryRepository.findAll();
            assertNotNull(categories);
            assertEquals(1, categories.size());
            assertEquals(1L, categories.get(0).getId());
            assertEquals("new description", categories.get(0).getDescription());
            assertEquals(3L, categories.get(0).getParentId());
    }
}

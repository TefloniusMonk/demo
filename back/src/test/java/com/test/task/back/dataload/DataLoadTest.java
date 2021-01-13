package com.test.task.back.dataload;

import com.test.task.back.dataload.api.DataLoader;
import com.test.task.back.dto.CatalogForm;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class DataLoadTest {
    @Autowired
    private DataLoader<CatalogForm> catalogDataLoader;

    @Test
    @SneakyThrows
    void shouldParseXml(){
        File file = new ClassPathResource("xml/catalog.xml").getFile();
        val result = catalogDataLoader.parse(new FileInputStream(file));
        assertNotNull(result);
        assertNotNull(result.getCategories());
        assertNotNull(result.getProducts());
        assertEquals(6, result.getCategories().size());
        assertEquals(3, result.getProducts().size());
    }

    @Test
    @SneakyThrows
    void shouldParseXmlWithCorrectFields(){
        File file = new ClassPathResource("xml/catalog_simple.xml").getFile();
        val result = catalogDataLoader.parse(new FileInputStream(file));
        assertNotNull(result);
        assertNotNull(result.getCategories());
        assertNotNull(result.getProducts());
        assertEquals(1, result.getCategories().size());
        assertEquals(1, result.getProducts().size());
        assertEquals(6, result.getCategories().get(0).getId());
        assertEquals(2, result.getCategories().get(0).getParentId());
        assertEquals("Нигири", result.getCategories().get(0).getDescription());
        assertEquals(1495, result.getProducts().get(0).getId());
        assertEquals(10, result.getProducts().get(0).getCategoryId());
        assertEquals("Ямато", result.getProducts().get(0).getName());
        assertEquals("Лосось, креветка, краб-микс, сыр, огурец, омлет, тобико, кунжут, соус «Ажи-маракуйя», "
                + "зеленый лук, 6 шт.", result.getProducts().get(0).getDescription());
        assertEquals(425, result.getProducts().get(0).getPrice());
        assertEquals("https://static4.tanuki.ru/product/1/5aKkVuNIiv9u3ypUj2a2sH-d6oU4J3f4.jpg", result.getProducts().get(0).getPictureUrl());
    }
}

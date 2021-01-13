package com.test.task.back.service;

import com.test.task.back.dataload.api.DataLoader;
import com.test.task.back.dto.CatalogForm;
import com.test.task.back.mapper.CategoryMapper;
import com.test.task.back.mapper.ProductMapper;
import com.test.task.back.service.api.CategoryService;
import com.test.task.back.service.api.DataLoadService;
import com.test.task.back.service.api.ProductService;
import com.test.task.back.type.LoadStatus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;

@Slf4j
@Service
@AllArgsConstructor
public class DefaultDataLoadService implements DataLoadService {
    private final DataLoader<CatalogForm> catalogDataLoader;
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;
    private final ProductService productService;
    private final ProductMapper productMapper;

    @Override
    public LoadStatus saveFromUrl(String url) {
        try {
            CatalogForm parsed = catalogDataLoader.parse(new URL(url).openStream());
            if (parsed != null) {
                if (parsed.getCategories() != null && parsed.getCategories().size() != 0) {
                    categoryService.save(categoryMapper.fromForms(parsed.getCategories()));
                }
                if (parsed.getProducts() != null && parsed.getProducts().size() != 0) {
                    productService.save(productMapper.fromForms(parsed.getProducts()));
                }
                log.debug("Received and parsed data: {}", parsed);
            } else {
                log.debug("Nothing was parsed");
            }
        } catch (IOException | SAXException | ParserConfigurationException e) {
            log.error("Error while fetching data: ", e);
            return LoadStatus.ERROR;
        }
        return LoadStatus.SUCCESS;
    }

}

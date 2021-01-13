package com.test.task.back.controller;

import com.test.task.back.db.document.Product;
import com.test.task.back.service.api.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/api/product")
@RestController
public class ProductController {
    private static final String CATEGORY_ID = "category_id";
    private final ProductService productService;
    @Value("#{'${permitted.search.fields}'.split(',')}")
    private List<String> permittedFields;

    @GetMapping("/")
    public List<Product> findByCriterias(HttpServletRequest request) {
        Map<String, String[]> params = request.getParameterMap();
        String searchedField = getSearchField(params);
        if (searchedField == null) {
            return Collections.emptyList();
        }
        return productService.findByCriterias(searchedField, params.get(searchedField)[0], getCategoryIdOrNull(params));
    }

    private Long getCategoryIdOrNull(Map<String, String[]> params) {
        if (params.containsKey(CATEGORY_ID)) {
            return Long.parseLong(params.get(CATEGORY_ID)[0]);
        }
        return null;
    }

    private String getSearchField(Map<String, String[]> params) {
        for (String field : params.keySet()) {
            if (permittedFields.contains(field)) {
                return field;
            }
        }
        return null;
    }
}

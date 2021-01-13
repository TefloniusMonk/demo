package com.test.task.back.dataload.sax;

import com.test.task.back.dto.CatalogForm;
import com.test.task.back.dto.CategoryForm;
import com.test.task.back.dto.ProductForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Stack;
import java.util.function.Consumer;

@Slf4j
@RequiredArgsConstructor
public class CatalogSaxHandler extends DefaultHandler {
    private final Consumer<CatalogForm> onFinish;
    private Stack<String> elementStack = new Stack<>();
    private CatalogForm catalog = new CatalogForm();
    private CategoryForm category = new CategoryForm();
    private ProductForm product = new ProductForm();

    @Override
    public void startElement(String uri, String localName, String qualifiedName, Attributes attributes) throws SAXException {
        elementStack.push(qualifiedName);
        if ("category".equalsIgnoreCase(qualifiedName)) {
            category = new CategoryForm(Long.parseLong(attributes.getValue("id")));
            if (attributes.getValue("parentId") != null) {
                category.setParentId(Long.parseLong(attributes.getValue("parentId")));
            }
        } else if ("product".equalsIgnoreCase(qualifiedName)) {
            product = new ProductForm(Long.parseLong(attributes.getValue("id")));
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String value = new String(ch, start, length).trim();
        if (value.isEmpty()) {
            return;
        }
        switch (currentElement().toLowerCase()) {
            case "category":
                category.setDescription(value);
                break;
            case "category_id":
                product.setCategoryId(Long.parseLong(value));
                break;
            case "description":
                product.setDescription(value);
                break;
            case "price":
                product.setPrice(Long.parseLong(value));
                break;
            case "name":
                product.setName(value);
                break;
            case "picture":
                product.setPictureUrl(value);
                break;
            default:
                log.debug("Unexpected value : {}", value);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qualifiedName) {
        elementStack.pop();
        switch (qualifiedName.toLowerCase()) {
            case "product":
                catalog.getProducts().add(product);
                break;
            case "category":
                catalog.getCategories().add(category);
                break;
            case "delivery_service":
                onFinish.accept(catalog);
                break;
        }
    }


    private String currentElement() {
        return elementStack.peek();
    }

}

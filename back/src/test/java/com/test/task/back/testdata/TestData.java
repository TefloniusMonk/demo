package com.test.task.back.testdata;

import com.test.task.back.db.document.Category;
import com.test.task.back.db.document.Product;
import lombok.experimental.UtilityClass;
import lombok.val;

import java.util.Arrays;
import java.util.List;

@UtilityClass
public class TestData {
    public Category newCategory() {
        val category = new Category();
        category.setId(1L);
        category.setDescription("desc");
        category.setParentId(2L);
        return category;
    }

    public Category newCategory2() {
        val category = new Category();
        category.setId(1L);
        category.setDescription("new description");
        category.setParentId(3L);
        return category;
    }

    public List<Category> newCategories() {
        return Arrays.asList(
                new Category(1L, 2L, "desc1"),
                new Category(3L, 4L, "desc2"),
                new Category(5L, 6L, "desc3")

        );
    }

    public Product newProduct() {
        return new Product(1L, 2L, "name", "description", 123L, "url");
    }

    public Product newProduct2() {
        return new Product(1L, 3L, "new name", "new desc", 1234L, "url2");
    }

    public List<Product> newProducts() {
        return  Arrays.asList(
          new Product(1495L, 10L, "Ямато", "Лосось, креветка, краб-микс, сыр, огурец, омлет, тобико, кунжут, соус «Ажи-маракуйя», зеленый лук, 6 шт.",
                  425L, "https://static4.tanuki.ru/product/1/5aKkVuNIiv9u3ypUj2a2sH-d6oU4J3f4.jpg") ,
                new Product(1496L, 10L, "Сэнсэй", "Креветка темпура, лосось, огурец, авокадо, сыр, перец чили, микс соусов, кунжут, 6 шт.",
                  425L, "https://static4.tanuki.ru/product/1/VYqSvmA1QAjn9PtENZ8bczpCnFnqPp28.jpg"),
                new Product(1521L, 113L, "Моджи Mymo \"Мята-шоколадная стружка\"", "Традиционное японское мороженое со вкусом мята-шоколадная стружка в тонком рисовом тесте. 6 шт.",
                        1140L, "https://static4.tanuki.ru/product/1/I-WUZ-aRwI3DxhZMV54TcEQgJ-ooYHt5.jpg"),
                new Product(1497L, 11L, "Ямато", "Лосось, креветка, краб-микс, сыр, огурец, омлет, тобико, кунжут, соус «Ажи-маракуйя», зеленый лук, 6 шт.",
                        425L, "https://static4.tanuki.ru/product/1/5aKkVuNIiv9u3ypUj2a2sH-d6oU4J3f4.jpg")

        );
    }
}

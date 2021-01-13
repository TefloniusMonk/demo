package com.test.task.back.db.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName="category")
public class Category {
    @Id
    private Long id;

    @Field(type = FieldType.Long)
    private Long parentId;

    @Field(type = FieldType.Text)
    private String description;

}

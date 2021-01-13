package com.test.task.back.dto;

import lombok.Data;

@Data
public class DataLoadForm {
    private String url;

    public DataLoadForm(String url) {
        this.url = url;
    }

    public DataLoadForm() {
    }

    public static DataLoadForm of(String url){
        return new DataLoadForm(url);
    }
}

package com.test.task.back.controller;

import com.test.task.back.dto.DataLoadForm;
import com.test.task.back.service.api.DataLoadService;
import com.test.task.back.type.LoadStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RequestMapping("/api/dataload")
@RestController
public class DataLoadController {
    private final DataLoadService dataLoadService;

    @PostMapping("/")
    public LoadStatus loadData(@RequestBody DataLoadForm form) {
        return dataLoadService.saveFromUrl(form.getUrl());
    }
}

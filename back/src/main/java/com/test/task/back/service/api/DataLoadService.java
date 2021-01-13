package com.test.task.back.service.api;

import com.test.task.back.type.LoadStatus;

public interface DataLoadService {
    LoadStatus saveFromUrl(String url);
}

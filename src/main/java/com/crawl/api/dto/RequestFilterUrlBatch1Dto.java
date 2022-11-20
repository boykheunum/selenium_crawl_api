package com.crawl.api.dto;

import lombok.Data;

@Data
public class RequestFilterUrlBatch1Dto {
    private int executionId;
    private String robotId;
    private String url;
    private String categoryName;
    private String addDate;
    private String updDate;
}

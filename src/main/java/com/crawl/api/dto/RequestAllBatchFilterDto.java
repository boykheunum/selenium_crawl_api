package com.crawl.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestAllBatchFilterDto {
    private String robot;
    private String batch;
    private int executionId;
}

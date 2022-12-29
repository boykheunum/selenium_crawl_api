package com.crawl.api.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseBatch2ResultDto {
    private int executionId;

    private String robotId;

    private String categoryName;

    private String url;

    private LocalDateTime addDate;

    private LocalDateTime updDate;

    private String productName;

    private String productKey;

    private String cpu;

    private String ram;

    private String storage;

    private String vga;

    private String monitor;

    private String status;

    private String originalPrice;

    private String Price;

    private String OperatingSystem;

    private String Color;
}

package com.crawl.api.dto;

import lombok.Data;

@Data
public class ResponseBatch3ResultDto {
    public ResponseBatch3ResultDto(String executionId, String robotId, String price, String originPrice, String productKey){
        this.executionId = executionId;
        this.robotId = robotId;
        this.price = price;
        this.originalPrice = originPrice;
        this.productKey = productKey;
    }

    private String id;

    private String executionId;

    private String robotId;

    private String status;

    private String view;

    private String cpu;

    private String ram;

    private String vga;

    private String monitor;

    private String color;

    private String promotional;

    private String originalPrice;

    private String price;

    private String productKey;

    private String categoryName1;

    private String categoryName2;

    private String categoryName3;

    private String addDate;

    private String updDate;

    private String storage;

    private String keyboard;

    private String url;
}

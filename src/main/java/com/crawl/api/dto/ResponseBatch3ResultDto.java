package com.crawl.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
public class ResponseBatch3ResultDto {
    public ResponseBatch3ResultDto(String executionId, String robotId, String price, String originPrice, String productKey) {
        this.executionId = executionId;
        this.robotId = robotId;
        this.price = price;
        this.originalPrice = originPrice;
        this.productKey = productKey;
    }

    public ResponseBatch3ResultDto(String executionId, String robotId, String view, String price, String originPrice,
                                   String productKey, String productName) {
        this.executionId = executionId;
        this.robotId = robotId;
        this.price = price;
        this.originalPrice = originPrice;
        this.productKey = productKey;
        this.view = view;
        this.productName = productName;
    }

    public ResponseBatch3ResultDto(String executionId, String robotId, String status, String view, String cpu, String ram,
                                   String vga, String monitor, String color, String promotional, String originalPrice,
                                   String price, String productKey, String categoryName1, String categoryName2,
                                   String categoryName3, String id, String addDate, String updDate, String storage,
                                   String keyboard, String url, String productName) {
        this.id = id;
        this.executionId = executionId;
        this.robotId = robotId;
        this.status = status;
        this.view = view;
        this.cpu = cpu;
        this.ram = ram;
        this.vga = vga;
        this.monitor = monitor;
        this.color = color;
        this.promotional = promotional;
        this.originalPrice = originalPrice;
        this.price = price;
        this.productKey = productKey;
        this.categoryName1 = categoryName1;
        this.categoryName2 = categoryName2;
        this.categoryName3 = categoryName3;
        this.addDate = addDate;
        this.updDate = updDate;
        this.storage = storage;
        this.keyboard = keyboard;
        this.url = url;
        this.productName = productName;
    }

    private String id;

    private String executionId;

    @JsonProperty("robot_id")
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

    @JsonProperty(value = "product_name")
    private String productName;
}

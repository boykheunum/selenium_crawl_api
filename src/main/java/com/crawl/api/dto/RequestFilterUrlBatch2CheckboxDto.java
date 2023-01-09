package com.crawl.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequestFilterUrlBatch2CheckboxDto {

    @JsonProperty(value = "execution_id")
    private int executionId;

    @JsonProperty(value = "robot_id")
    private String robotId;

    @JsonProperty(value = "category_name")
    private String categoryName;

    @JsonProperty(value = "url")
    private String url;

    @JsonProperty(value = "add_date")
    private LocalDateTime addDate;

    @JsonProperty(value = "upd_date")
    private LocalDateTime updDate;

    @JsonProperty(value = "add_date_start")
    private String addDateStart;

    @JsonProperty(value = "upd_date_start")
    private String updDateStart;

    @JsonProperty(value = "add_date_end")
    private String addDateEnd;

    @JsonProperty(value = "upd_date_end")
    private String updDateEnd;

    @JsonProperty(value = "product_name")
    private String productName;

    @JsonProperty(value = "product_key")
    private String productKey;

    @JsonProperty(value = "cpu")
    private String cpu;

    @JsonProperty(value = "ram")
    private String ram;

    @JsonProperty(value = "storage")
    private String storage;

    @JsonProperty(value = "vga")
    private String vga;

    @JsonProperty(value = "monitor")
    private String monitor;

    @JsonProperty(value = "status")
    private String status;

    @JsonProperty(value = "original_price")
    private String originalPrice;

    @JsonProperty(value = "original_price_min")
    private String originalPriceMin;

    @JsonProperty(value = "original_price_max")
    private String originalPriceMax;

    @JsonProperty(value = "price")
    private String Price;

    @JsonProperty(value = "price_min")
    private String priceMin;

    @JsonProperty(value = "price_max")
    private String priceMax;

    @JsonProperty(value = "operating_system")
    private String OperatingSystem;

    @JsonProperty(value = "color")
    private String Color;
}

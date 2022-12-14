package com.crawl.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBatch2ResultDto {

    private int id;

    @JsonProperty(value = "execution_id")
    private int executionId;

    @JsonProperty(value = "robot_id")
    private String robotId;

    @JsonProperty(value = "url")
    private String url;

    @JsonProperty(value = "add_date")
    private LocalDateTime addDate;

    @JsonProperty(value = "upd_date")
    private LocalDateTime updDate;

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

    @JsonProperty(value = "price")
    private String Price;

    @JsonProperty(value = "operating_system")
    private String OperatingSystem;

    @JsonProperty(value = "color")
    private String Color;
}

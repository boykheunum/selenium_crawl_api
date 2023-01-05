package com.crawl.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequestFilterUrlBatch1CheckboxDto {

    @JsonProperty(value = "execution_id")
    private int executionId;
    @JsonProperty(value = "robot_id")
    private String robotId;
    @JsonProperty(value = "url")
    private String url;
    @JsonProperty(value = "category_name")
    private String categoryName;
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
}

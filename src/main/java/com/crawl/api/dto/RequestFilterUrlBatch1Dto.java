package com.crawl.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RequestFilterUrlBatch1Dto {

    @JsonProperty(value = "execution_id")
    private int executionId;
    @JsonProperty(value = "robot_id")
    private String robotId;
    @JsonProperty(value = "url")
    private String url;
    @JsonProperty(value = "category_name")
    private String categoryName;
    @JsonProperty(value = "add_date")
    private String addDate;
    @JsonProperty(value = "upd_date")
    private String updDate;
}

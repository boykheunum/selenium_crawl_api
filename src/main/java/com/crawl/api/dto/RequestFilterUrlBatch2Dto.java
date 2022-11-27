package com.crawl.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RequestFilterUrlBatch2Dto {
    @JsonProperty(value = "execution_id")
    private int executionId;
    @JsonProperty(value = "robot_id")
    private String robotId;
    @JsonProperty(value = "url")
    private String url;

}

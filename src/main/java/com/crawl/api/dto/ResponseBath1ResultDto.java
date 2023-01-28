package com.crawl.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ResponseBath1ResultDto {

    public ResponseBath1ResultDto(String robotId, String categoryName, int executionId,
                                  String url, LocalDateTime addDate, LocalDateTime updDate) {
        this.robotId = robotId;
        this.categoryName = categoryName;
        this.executionId = executionId;
        this.url = url;
        this.addDate = addDate;
        this.updDate = updDate;
    }

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

}

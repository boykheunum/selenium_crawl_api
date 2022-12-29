package com.crawl.api.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseBath1ResultDto {

    private int executionId;

    private String robotId;

    private String categoryName;

    private String url;

    private LocalDateTime addDate;

    private LocalDateTime updDate;

}

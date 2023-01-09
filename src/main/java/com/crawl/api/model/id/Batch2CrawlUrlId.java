package com.crawl.api.model.id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Batch2CrawlUrlId implements Serializable {
    private int id;

    private String robotId;

    private int executionId;
}
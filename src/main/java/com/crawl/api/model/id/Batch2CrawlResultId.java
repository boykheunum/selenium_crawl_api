package com.crawl.api.model.id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Batch2CrawlResultId implements Serializable {

    private int id;
    private int executionId;
    private String robotId;
    private String productKey;
}

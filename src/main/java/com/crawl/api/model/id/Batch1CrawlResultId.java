package com.crawl.api.model.id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Batch1CrawlResultId implements Serializable {

    private int executionId;
    private String robotId;
    private String categoryName;
}

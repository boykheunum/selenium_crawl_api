package com.crawl.api.model.id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Batch3CrawlResultId implements Serializable {

    private String id;
    private String executionId;
    private String robotId;
}

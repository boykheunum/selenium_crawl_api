package com.crawl.api.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@SqlResultSetMapping(name = "FILTER_URL_FOR_BATCH2", classes = {@ConstructorResult(
        targetClass = com.crawl.api.dto.RequestFilterUrlBatch1Dto.class,
        columns = {
                @ColumnResult(name = "execution_id", type = Integer.class),
                @ColumnResult(name = "robot_id", type = String.class),
                @ColumnResult(name = "category_name", type = String.class),
                @ColumnResult(name = "url", type = String.class),
                @ColumnResult(name = "add_date", type = String.class),
                @ColumnResult(name = "udp_date", type = String.class)
        })})

@Entity
@Data
@Table(name = "tbl_batch1_crawl_result")
public class Batch1CrawlResultModel {
    @Id
    private int executionId;

    @Column(name = "robot_id")
    private String robotId;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "url")
    private String url;

    @Column(name = "add_date")
    private LocalDateTime addDate;

    @Column(name = "upd_date")
    private LocalDateTime updDate;
}

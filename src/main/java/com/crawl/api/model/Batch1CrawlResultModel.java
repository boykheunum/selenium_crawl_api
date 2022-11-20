package com.crawl.api.model;

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
@Table(name = "tbl_batch1_crawl_result")
public class Batch1CrawlResultModel {
    @Id
    private int executionId;

    @Column(name = "RobotId")
    private String RobotId;

    @Column(name = "CategoryName")
    private String categoryName;

    @Column(name = "Url")
    private String url;

    @Column(name = "AddDate")
    private LocalDateTime addDate;

    @Column(name = "UpdDate")
    private LocalDateTime UpdDate;
}

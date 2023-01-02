package com.crawl.api.model;

import com.crawl.api.model.id.Batch1CrawlResultId;
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
@IdClass(Batch1CrawlResultId.class)
public class Batch1CrawlResultModel extends BaseModel {
    @Id
    @Column(name = "execution_id")
    private int executionId;

    @Id
    @Column(name = "robot_id")
    private String robotId;

    @Id
    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "url")
    private String url;

}

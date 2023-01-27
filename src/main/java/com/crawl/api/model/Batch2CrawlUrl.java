package com.crawl.api.model;

import com.crawl.api.model.id.Batch1CrawlResultId;
import com.crawl.api.model.id.Batch2CrawlUrlId;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tbl_batch2_crawl_url")
@Data
@IdClass(Batch2CrawlUrlId.class)
public class Batch2CrawlUrl {
    @Id
    private int id;
    @Id
    @Column(name = "robot_id")
    private String robotId;
    @Id
    @Column(name = "execution_id")
    private int executionId;

    @Column(name = "url")
    private String url;
}

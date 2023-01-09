package com.crawl.api.model;

import com.crawl.api.model.id.Batch3CrawlListId;

import javax.persistence.*;

@Entity
@Table(name = "tbl_batch3_crawl_list")
@IdClass(Batch3CrawlListId.class)
public class Batch3CrawlList extends BaseModel {
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

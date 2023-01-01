package com.crawl.api.model;

import com.crawl.api.model.id.Batch2CrawlResultId;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@SqlResultSetMapping(name = "FILTER_URL_FOR_BATCH3", classes = {@ConstructorResult(
        targetClass = com.crawl.api.dto.RequestFilterUrlBatch1Dto.class,
        columns = {
                @ColumnResult(name = "Id", type = Integer.class),
                @ColumnResult(name = "execution_id", type = Integer.class),
                @ColumnResult(name = "robot_id", type = String.class),
                @ColumnResult(name = "productName", type = String.class),
                @ColumnResult(name = "url", type = String.class),
                @ColumnResult(name = "add_date", type = String.class),
                @ColumnResult(name = "udp_date", type = String.class)
        })})

@Entity
@Table(name = "tbl_batch2_crawl_result")
@Data
@IdClass(Batch2CrawlResultId.class)
public class Batch2CrawlResultModel {
    @Id
    @Column(name = "Id")
    private int id;

    @Id
    @Column(name = "execution_id")
    private int executionId;

    @Id
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

    @Column(name = "product_name")
    private String productName;

    @Id
    @Column(name = "product_key")
    private String productKey;

    @Column(name = "cpu")
    private String cpu;

    @Column(name = "ram")
    private String ram;

    @Column(name = "storage")
    private String storage;

    @Column(name = "vga")
    private String vga;

    @Column(name = "monitor")
    private String monitor;

    @Column(name = "status")
    private String status;

    @Column(name = "original_price")
    private String originalPrice;

    @Column(name = "price")
    private String Price;

    @Column(name = "operatingSystem")
    private String OperatingSystem;

    @Column(name = "color")
    private String Color;
}

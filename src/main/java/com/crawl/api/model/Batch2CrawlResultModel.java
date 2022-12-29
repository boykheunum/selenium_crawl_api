package com.crawl.api.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@SqlResultSetMapping(name = "FILTER_URL_FOR_BATCH3", classes = {@ConstructorResult(
        targetClass = com.crawl.api.dto.RequestFilterUrlBatch1Dto.class,
        columns = {
                @ColumnResult(name = "execution_id", type = Integer.class),
                @ColumnResult(name = "robot_id", type = String.class),
                @ColumnResult(name = "url", type = String.class),
                @ColumnResult(name = "add_date", type = String.class),
                @ColumnResult(name = "udp_date", type = String.class)
        })})

@Entity
@Table(name = "tbl_batch2_crawl_result")
@Data
public class Batch2CrawlResultModel {
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

    @Column(name = "product_name")
    private String productName;

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

    @Column(name = "Price")
    private String Price;

    @Column(name = "OperatingSystem")
    private String OperatingSystem;

    @Column(name = "Color")
    private String Color;
}

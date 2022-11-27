package com.crawl.api.model;

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
public class Batch2CrawlResultModel {
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

    @Column(name = "ProductName")
    private String productName;

    @Column(name = "ProductKey")
    private String productKey;

    @Column(name = "CPU")
    private String CPU;

    @Column(name = "Ram")
    private String Ram;

    @Column(name = "Storage")
    private String Storage;

    @Column(name = "VGA")
    private String VGA;

    @Column(name = "Monitor")
    private String Monitor;

    @Column(name = "Status")
    private String Status;

    @Column(name = "OriginalPrice")
    private String OriginalPrice;

    @Column(name = "Price")
    private String Price;

    @Column(name = "OperatingSystem")
    private String OperatingSystem;

    @Column(name = "Color")
    private String Color;
}

package com.crawl.api.model;

import com.crawl.api.model.id.Batch2CrawlResultId;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;


@SqlResultSetMapping(name = "FILTER_URL_FOR_BATCH3", classes = {@ConstructorResult(//TODO
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
@SqlResultSetMapping(name = "FILTER_RESULT_FOR_BATCH2", classes = {@ConstructorResult(
        targetClass = com.crawl.api.dto.ResponseBatch2ResultDto.class,
        columns = {
                @ColumnResult(name = "id", type = Integer.class),
                @ColumnResult(name = "executionId", type = Integer.class),
                @ColumnResult(name = "robotId", type = String.class),
                @ColumnResult(name = "categoryName", type = String.class),
                @ColumnResult(name = "url", type = String.class),
                @ColumnResult(name = "addDate", type = LocalDateTime.class),
                @ColumnResult(name = "updDate", type = LocalDateTime.class),
                @ColumnResult(name = "productName", type = String.class),
                @ColumnResult(name = "productKey", type = String.class),
                @ColumnResult(name = "cpu", type = String.class),
                @ColumnResult(name = "ram", type = String.class),
                @ColumnResult(name = "storage", type = String.class),
                @ColumnResult(name = "vga", type = String.class),
                @ColumnResult(name = "monitor", type = String.class),
                @ColumnResult(name = "status", type = String.class),
                @ColumnResult(name = "originalPrice", type = String.class),
                @ColumnResult(name = "price", type = String.class),
                @ColumnResult(name = "operatingSystem", type = String.class),
                @ColumnResult(name = "color", type = String.class)
        })})

@Entity
@Table(name = "tbl_batch2_crawl_result")
@Data
@IdClass(Batch2CrawlResultId.class)
public class Batch2CrawlResultModel extends BaseModel{
    @Id
    @Column(name = "id")
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

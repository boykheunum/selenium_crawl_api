package com.crawl.api.model;

import com.crawl.api.model.id.Batch3CrawlResultId;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tbl_batch3_crawl_result")
@Data
@IdClass(Batch3CrawlResultId.class)
public class Batch3CrawlResultModel {
    @Id
    @Column(name = "Id")
    private String id;

    @Id
    @Column(name = "execution_id")
    private String executionId;

    @Id
    @Column(name = "robot_id")
    private String robotId;

    @Column(name = "status")
    private String status;

    @Column(name = "view")
    private String view;

    @Column(name = "cpu")
    private String cpu;

    @Column(name = "ram")
    private String ram;

    @Column(name = "vga")
    private String vga;

    @Column(name = "monitor")
    private String monitor;

    @Column(name = "color")
    private String color;

    @Column(name = "promotional")
    private String promotional;

    @Column(name = "original_price")
    private String originalPrice;

    @Column(name = "price")
    private String price;

    @Column(name = "product_key")
    private String productKey;

    @Column(name = "category_name_1")
    private String categoryName1;

    @Column(name = "category_name_2")
    private String categoryName2;

    @Column(name = "category_name_3")
    private String categoryName3;

    @Column(name = "add_date")
    private String addDate;

    @Column(name = "udp_date")
    private String updDate;

    @Column(name = "storage")
    private String storage;

    @Column(name = "keyboard")
    private String keyboard;

    @Column(name = "url")
    private String url;
}
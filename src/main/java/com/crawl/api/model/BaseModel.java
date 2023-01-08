package com.crawl.api.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
public class BaseModel {
    @Column(name = "add_date")
    private LocalDateTime addDate;

    @Column(name = "upd_date")
    private LocalDateTime updDate;
}

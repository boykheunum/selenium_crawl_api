package com.crawl.api.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public class BaseModel {
    @Column(name = "add_date")
    private LocalDateTime addDate;

    @Column(name = "upd_date")
    private LocalDateTime updDate;
}

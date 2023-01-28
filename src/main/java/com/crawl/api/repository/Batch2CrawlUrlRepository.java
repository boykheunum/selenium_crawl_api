package com.crawl.api.repository;

import com.crawl.api.model.Batch2CrawlUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface Batch2CrawlUrlRepository extends JpaRepository<Batch2CrawlUrl, Integer> {
    @Modifying
    @Query(value = "DELETE FROM tbl_batch2_crawl_url WHERE id = :id", nativeQuery = true)
    void deleteSelectId(@Param(value = "id")String id);
}

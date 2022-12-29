package com.crawl.api.repository;

import com.crawl.api.model.Batch2CrawlResultModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Batch2Repository extends JpaRepository<Batch2CrawlResultModel, Integer> {
    @Query(value = "SELECT * FROM tbl_batch2_crawl_result AS b WHERE b.robot_id = :robotId", nativeQuery = true)
    List<Batch2CrawlResultModel>getBatch2Result(String robotId);
}

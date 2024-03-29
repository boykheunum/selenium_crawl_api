package com.crawl.api.repository;

import com.crawl.api.model.Batch3CrawlResultModel;
import com.crawl.api.model.id.Batch3CrawlResultId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Batch3Repository extends JpaRepository<Batch3CrawlResultModel, String> {
    @Query(value = "SELECT * FROM tbl_batch3_crawl_result WHERE robot_id = :robotId", nativeQuery = true)
    List<Batch3CrawlResultModel> getBatch3Result(String robotId);

    @Query(value = "SELECT distinct execution_id FROM tbl_batch3_crawl_result WHERE robot_id = :robotId order by execution_id DESC", nativeQuery = true)
    List<Integer> getDistinctExecutionId(@Param("robotId")String robotId);
}

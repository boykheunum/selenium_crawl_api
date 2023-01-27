package com.crawl.api.repository;

import com.crawl.api.model.Batch1CrawlResultModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Batch1Repository extends JpaRepository<Batch1CrawlResultModel, Integer> {
    @Query(value = "SELECT * FROM tbl_batch1_crawl_result WHERE robot_id = :robotId", nativeQuery = true)
    List<Batch1CrawlResultModel> getBatch1Result(@Param("robotId")String robotId);

    @Query(value = "SELECT distinct execution_id FROM tbl_batch1_crawl_result order by execution_id DESC", nativeQuery = true)
    List<Integer> getDistinctExecutionId();
}

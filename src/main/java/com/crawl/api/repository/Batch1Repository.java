package com.crawl.api.repository;

import com.crawl.api.dto.ResponseBath1ResultDto;
import com.crawl.api.model.Batch1CrawlResultModel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Batch1Repository extends JpaRepository<Batch1CrawlResultModel, Integer> {
    @Query(name = "SELECT * FROM tbl_batch1_crawl_result WHERE RobotId = :robotId", nativeQuery = true)
    List<ResponseBath1ResultDto> getBatch1Result(String robotId);
}

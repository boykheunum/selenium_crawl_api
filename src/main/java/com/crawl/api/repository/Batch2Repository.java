package com.crawl.api.repository;

import com.crawl.api.model.Batch2CrawlResultModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Batch2Repository extends JpaRepository<Batch2CrawlResultModel, Integer> {
}

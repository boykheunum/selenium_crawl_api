package com.crawl.api.repository;

import com.crawl.api.model.Batch1CrawlResultModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Batch1Repository extends JpaRepository<Batch1CrawlResultModel, Integer> {

}

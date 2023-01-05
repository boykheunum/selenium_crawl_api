package com.crawl.api.repository;

import com.crawl.api.model.Batch3CrawlList;
import com.crawl.api.model.id.Batch3CrawlListId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Batch3CrawlListRepository extends JpaRepository<Batch3CrawlList, Batch3CrawlListId> {
}

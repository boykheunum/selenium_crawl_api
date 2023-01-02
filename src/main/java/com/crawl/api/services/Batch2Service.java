package com.crawl.api.services;

import com.crawl.api.dto.RequestFilterUrlBatch2Dto;
import com.crawl.api.dto.ResponseBatch2ResultDto;

import java.util.List;

public interface Batch2Service {
    void Batch3UrlFilter(RequestFilterUrlBatch2Dto dto);

    List<ResponseBatch2ResultDto> getListBatch2Result(Integer robotId);

    void deleteAllBatch2CrawlUrls();
}

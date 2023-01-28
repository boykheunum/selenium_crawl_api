package com.crawl.api.services;

import com.crawl.api.dto.RequestFilterUrlBatch2CheckboxDto;
import com.crawl.api.dto.RequestFilterUrlBatch2Dto;
import com.crawl.api.dto.ResponseBatch2ResultDto;
import com.crawl.api.model.Batch2CrawlUrl;

import java.util.List;

public interface Batch2Service {
    void Batch3UrlFilter(RequestFilterUrlBatch2Dto dto);

    void Batch3UrlFilterCheckbox(RequestFilterUrlBatch2CheckboxDto dto);

    List<ResponseBatch2ResultDto> getListBatch2Result(Integer robotId);

    List<ResponseBatch2ResultDto> getListBatch2ResultFilter(ResponseBatch2ResultDto dto);

    void deleteAllBatch2CrawlUrls();

    List<Batch2CrawlUrl> getAllBatch2CrawlUrls();

    void batch2CrawlUrlDeleteAll();

    void deleteBatch2DataSelect(String idSelect);

    List<Integer> getBatch2ExecutionId(String robotId);
}

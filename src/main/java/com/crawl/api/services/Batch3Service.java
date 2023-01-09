package com.crawl.api.services;

import com.crawl.api.dto.ResponseBatch3ResultDto;

import java.util.List;

public interface Batch3Service {

    List<ResponseBatch3ResultDto> getListBatch3Result(Integer robotId);

    void deleteAllBatch3CrawlLists();

    List<ResponseBatch3ResultDto> getChartData();
}

package com.crawl.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.crawl.api.dto.RequestFilterUrlBatch1Dto;
import com.crawl.api.dto.ResponseBath1ResultDto;

public interface Batch1Service {
    void Batch2UrlFilter(RequestFilterUrlBatch1Dto dto);

    List<ResponseBath1ResultDto> Batch1Result(String robotId);
}

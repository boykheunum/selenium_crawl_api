package com.crawl.api.services.impl;

import com.crawl.api.dto.RequestFilterUrlBatch1Dto;
import com.crawl.api.repository.CustomRepository.Batch1CustomRepository;
import com.crawl.api.services.Batch1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Batch1ServicesImpl implements Batch1Service {
    @Autowired
    private Batch1CustomRepository batch1Custom;
    @Override
    public void Batch2UrlFilter(RequestFilterUrlBatch1Dto dto) {
        batch1Custom.filterUrlForBatch2(dto);
    }
}

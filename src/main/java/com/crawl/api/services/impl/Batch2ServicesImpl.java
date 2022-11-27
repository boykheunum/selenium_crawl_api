package com.crawl.api.services.impl;

import com.crawl.api.dto.RequestFilterUrlBatch2Dto;
import com.crawl.api.repository.CustomRepository.Batch2CustomRepository;
import com.crawl.api.services.Batch2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Batch2ServicesImpl implements Batch2Service {
    @Autowired
    private Batch2CustomRepository batch2Custom;

    @Override
    public void Batch3UrlFilter(RequestFilterUrlBatch2Dto dto) {
        batch2Custom.filterUrlForBatch3(dto);
    }
}

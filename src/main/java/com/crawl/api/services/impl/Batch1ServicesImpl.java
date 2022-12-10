package com.crawl.api.services.impl;

import com.crawl.api.dto.RequestFilterUrlBatch1Dto;
import com.crawl.api.dto.ResponseBath1ResultDto;
import com.crawl.api.repository.Batch1Repository;
import com.crawl.api.repository.CustomRepository.Batch1CustomRepository;
import com.crawl.api.services.Batch1Service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Batch1ServicesImpl implements Batch1Service {
    @Autowired
    private Batch1CustomRepository batch1Custom;
    @Autowired
    private Batch1Repository batch1Repository;
    private ModelMapper modelMapper;
    @Override
    public void Batch2UrlFilter(RequestFilterUrlBatch1Dto dto) {
        batch1Custom.filterUrlForBatch2(dto);
    }

    @Override
    public List<ResponseBath1ResultDto> Batch1Result(String robotId) {
        // TODO Auto-generated method stub
        List<ResponseBath1ResultDto> data = batch1Repository.getBatch1Result(robotId);
        
        return data;
    }
}

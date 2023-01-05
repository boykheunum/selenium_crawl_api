package com.crawl.api.services.impl;

import com.crawl.api.common.Contains;
import com.crawl.api.common.untils.ModelMapUntils;
import com.crawl.api.dto.RequestFilterUrlBatch1CheckboxDto;
import com.crawl.api.dto.RequestFilterUrlBatch1Dto;
import com.crawl.api.dto.ResponseBatch2ResultDto;
import com.crawl.api.dto.ResponseBath1ResultDto;
import com.crawl.api.model.Batch1CrawlResultModel;
import com.crawl.api.repository.Batch1Repository;
import com.crawl.api.repository.CustomRepository.Batch1CustomRepository;
import com.crawl.api.services.Batch1Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.el.stream.Stream;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Batch1ServicesImpl implements Batch1Service {
    @Autowired
    private Batch1CustomRepository batch1Custom;
    @Autowired
    private Batch1Repository batch1Repository;

    @Override
    public void Batch2UrlFilter(RequestFilterUrlBatch1Dto dto) {
        batch1Custom.filterUrlForBatch2(dto);
    }

    @Override
    public void Batch2UrlFilterCheckbox(RequestFilterUrlBatch1CheckboxDto dto) {
        batch1Custom.filterUrlForBatch2Checkbox(dto);
    }

    @Override
    public List<ResponseBath1ResultDto> Batch1Result(String robotId) {
        // TODO Auto-generated method stub
        if (robotId.equals(String.valueOf(Contains.TypeRobot.TYPE_HACOM_BATCH1))) {
            robotId = "HACOM";
        } else if (robotId.equals(String.valueOf(Contains.TypeRobot.TYPE_NCPC_BATCH1))) {
            robotId = "NCPC";
        } else if (robotId.equals(String.valueOf(Contains.TypeRobot.TYPE_ALL))) {
            List<Batch1CrawlResultModel> lBatch1Result = batch1Repository.findAll();
            return ModelMapUntils.mapAll(lBatch1Result, ResponseBath1ResultDto.class);
        }
        List<Batch1CrawlResultModel> result = batch1Repository.getBatch1Result(robotId);
        List<ResponseBath1ResultDto> data = ModelMapUntils.mapAll(result, ResponseBath1ResultDto.class);
        return data;
    }
}

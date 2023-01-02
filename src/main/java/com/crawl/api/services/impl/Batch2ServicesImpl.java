package com.crawl.api.services.impl;

import com.crawl.api.common.Contains;
import com.crawl.api.common.untils.ModelMapUntils;
import com.crawl.api.dto.RequestFilterUrlBatch2Dto;
import com.crawl.api.dto.ResponseBatch2ResultDto;
import com.crawl.api.model.Batch2CrawlResultModel;
import com.crawl.api.repository.Batch2Repository;
import com.crawl.api.repository.CustomRepository.Batch2CustomRepository;
import com.crawl.api.services.Batch2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Batch2ServicesImpl implements Batch2Service {
    @Autowired
    private Batch2CustomRepository batch2Custom;
    @Autowired
    private Batch2Repository batch2Repository;

    @Override
    public void Batch3UrlFilter(RequestFilterUrlBatch2Dto dto) {
        batch2Custom.filterUrlForBatch3(dto);
    }

    @Override
    public List<ResponseBatch2ResultDto> getListBatch2Result(Integer type) {
        String robotId ="HACOM02";
        if(type == Contains.TypeRobot.TYPE_HACOM_BATCH2){
            robotId ="HACOM02";
        } else if (type == Contains.TypeRobot.TYPE_NCPC_BATCH2) {
            robotId = "NCPC02";
        } else if (type == Contains.TypeRobot.TYPE_ALL) {
            List<Batch2CrawlResultModel> lBatch2Result = batch2Repository.findAll();
            return ModelMapUntils.mapAll(lBatch2Result, ResponseBatch2ResultDto.class);
        }
        List<Batch2CrawlResultModel> lBatch2Result = batch2Repository.getBatch2Result(robotId);

        return ModelMapUntils.mapAll(lBatch2Result, ResponseBatch2ResultDto.class);
    }
}

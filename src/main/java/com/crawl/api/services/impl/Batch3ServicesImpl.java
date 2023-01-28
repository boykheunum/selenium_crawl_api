package com.crawl.api.services.impl;

import com.crawl.api.common.Contains;
import com.crawl.api.common.untils.ModelMapUntils;
import com.crawl.api.dto.ResponseBatch2ResultDto;
import com.crawl.api.dto.ResponseBatch3ResultDto;
import com.crawl.api.model.Batch2CrawlResultModel;
import com.crawl.api.model.Batch3CrawlResultModel;
import com.crawl.api.repository.Batch3CrawlListRepository;
import com.crawl.api.repository.Batch3Repository;
import com.crawl.api.repository.CustomRepository.Batch3CustomRepository;
import com.crawl.api.services.Batch3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Batch3ServicesImpl implements Batch3Service {
    @Autowired
    private Batch3Repository batch3Repository;

    @Autowired
    private Batch3CrawlListRepository batch3CrawlListRepository;
    @Autowired
    private Batch3CustomRepository batch3CustomRepository;

    @Override
    public List<ResponseBatch3ResultDto> getListBatch3Result(Integer type) {
        String robotId ="HACOM03";
        if(type == Contains.TypeRobot.TYPE_HACOM_BATCH3){
            robotId ="HACOM03";
        } else if (type == Contains.TypeRobot.TYPE_NCPC_BATCH3) {
            robotId = "NCPC03";
        } else if (type == Contains.TypeRobot.TYPE_ALL) {
            List<Batch3CrawlResultModel> lBatch3Result = batch3Repository.findAll();
            return ModelMapUntils.mapAll(lBatch3Result, ResponseBatch3ResultDto.class);
        }
        List<Batch3CrawlResultModel> lBatch3Result = batch3Repository.getBatch3Result(robotId);

        return ModelMapUntils.mapAll(lBatch3Result, ResponseBatch3ResultDto.class);
    }

    @Override
    public void deleteAllBatch3CrawlLists() {
        batch3CrawlListRepository.deleteAll();
    }

    @Override
    public List<ResponseBatch3ResultDto> getChartData() {
        List<ResponseBatch3ResultDto> data = batch3CustomRepository.getDataFromBatch3();
        return data;
    }

    @Override
    public List<ResponseBatch3ResultDto> getListBatch3ResultFilter(ResponseBatch3ResultDto dto) {
        List<Batch3CrawlResultModel> lBatch3Result = batch3CustomRepository.getBatch3ResultFilter(dto);

        return ModelMapUntils.mapAll(lBatch3Result, ResponseBatch3ResultDto.class);
    }

    @Override
    public List<Integer> getBatch3ExecutionId(String robotId) {
        return batch3Repository.getDistinctExecutionId(robotId);
    }
}

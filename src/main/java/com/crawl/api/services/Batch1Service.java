package com.crawl.api.services;

import java.util.List;

import com.crawl.api.dto.*;
import com.crawl.api.model.Batch1CrawlResultModel;
import org.springframework.stereotype.Service;

public interface Batch1Service {
    void Batch2UrlFilter(RequestFilterUrlBatch1Dto dto);

    void Batch2UrlFilterCheckbox(RequestFilterUrlBatch1CheckboxDto dto);

    List<ResponseBath1ResultDto> Batch1Result(String robotId);

    void exportBatch1Excel(RequestDataExportDto data);

    void exportBatch1Csv(RequestDataExportDto data);

    List<ResponseBath1ResultDto> getBatch1ResultFilter(RequestFilterUrlBatch1CheckboxDto dto);

    List<Integer> getBatch1ExecutionId(String robotId);
}

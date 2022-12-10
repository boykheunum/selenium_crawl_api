package com.crawl.api.repository.CustomRepository;

import com.crawl.api.common.Contains;
import com.crawl.api.common.StringUtils;
import com.crawl.api.dto.RequestFilterUrlBatch1Dto;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Repository
@Transactional
public class Batch1CustomRepository extends BaseRepositoryCustom {
    public final static String FILTER_URL_FOR_BATCH2 = "FILTER_URL_FOR_BATCH2";

    public void filterUrlForBatch2(RequestFilterUrlBatch1Dto dto) {
        StringBuilder sb = new StringBuilder();
        Map<String, Object> params = new HashMap<String, Object>();
        sb.append("INSERT INTO tbl_batch2_crawl_url(RobotId, ExecutionId, Url)")
                .append("(SELECT 'HACOM02' AS robotId, ExecutionId AS executionId, ")
                .append("Url AS url FROM tbl_batch1_crawl_result AS b1 WHERE RobotId = :robotId AND ");
        if (StringUtils.integerIsNull(dto.getExecutionId()) || dto.getExecutionId() == 0) {
            sb.append("ExecutionId = (SELECT MAX(ExecutionId) FROM tbl_batch1_crawl_result GROUP BY ExecutionId) ");
        } else {
            sb.append("ExecutionId = :executionId ");
            params.put("executionId", dto.getExecutionId());
        }
        sb.append("LIMIT :limit)");
        params.put("robotId", dto.getRobotId());
        params.put("limit", Contains.Batch1.LIMIT_URL_FOR_BATCH_2);
        System.out.println(sb.toString());
        Integer obj = getSelectResult(sb.toString(), FILTER_URL_FOR_BATCH2, params);
        return;
    }
}

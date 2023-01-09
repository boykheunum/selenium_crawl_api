package com.crawl.api.repository.CustomRepository;

import com.crawl.api.common.Contains;
import com.crawl.api.dto.ResponseBatch3ResultDto;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class Batch3CustomRepository extends BaseRepositoryCustom {
    public static final String DATA_FOR_CHART = "DATA_FOR_CHART";

    public List<ResponseBatch3ResultDto> getDataFromBatch3() {
        StringBuilder str = new StringBuilder();
        Map<String, Object> params = new HashMap<String, Object>();
        str.append("SELECT original_price AS originPrice, price AS price, product_key AS productKey, `view`, " +
                "robot_id AS robotId, execution_id AS executionId, product_name AS productName " +
                "FROM tbl_batch3_crawl_result WHERE execution_id = " +
                "(SELECT MAX(execution_id) FROM tbl_batch3_crawl_result WHERE robot_id = 'HACOM03') " +
                "UNION SELECT original_price AS originPrice, price AS price, product_key AS productKey, `view`, " +
                "robot_id AS robotId, execution_id AS executionId, product_name AS productName " +
                "FROM tbl_batch3_crawl_result WHERE execution_id = " +
                "(SELECT MAX(execution_id) FROM tbl_batch3_crawl_result WHERE robot_id = 'NCPC03')");
        return getResultList(str.toString(), DATA_FOR_CHART, params);
    }
}

package com.crawl.api.repository.CustomRepository;

import com.crawl.api.common.Contains;
import com.crawl.api.dto.ResponseBatch3ResultDto;
import com.crawl.api.model.Batch3CrawlResultModel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class Batch3CustomRepository extends BaseRepositoryCustom {
    public static final String DATA_FOR_CHART = "DATA_FOR_CHART";
    public static final String FILTER_RESULT_FOR_BATCH3 = "FILTER_RESULT_FOR_BATCH3";

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

    public List<Batch3CrawlResultModel> getBatch3ResultFilter(ResponseBatch3ResultDto dto) {
        StringBuilder sb = new StringBuilder();
        Map<String, Object> params = new HashMap<String, Object>();
        sb.append("SELECT b.id As id, b.robot_id as RobotID, b.product_key as productKey, b.execution_id as executionId, b.status as status, b.view as view, b.cpu as cpu, b.ram as ram, b.vga as vga, b.monitor as monitor, b.color as color, b.promotional as promotional, b.original_price as originalPrice, b.price as price, b.product_key as productKey, b.storage as storage, b.keyboard as keyboard, b.url as url,")
                .append("b.category_name_1 as categoryName1, b.category_name_2 as categoryName2, b.category_name_3 as categoryName3, b.add_date as addDate, b.upd_date as updDate, b.product_name as productName ")
                .append("FROM tbl_batch3_crawl_result as b ");
        if(dto != null){
            sb.append("WHERE 1 ");
            if (dto.getRobotId() != null && !dto.getRobotId().equals("")) {
                sb.append("AND robot_id = :robotId ");
                params.put("robotId", dto.getRobotId());
            }
            if (dto.getProductName() != null && !dto.getProductName().equals("")) {
                sb.append("AND product_name LIKE :productName ");
                params.put("productName", "%" + dto.getProductName() + "%");
            }
            if (dto.getColor() != null && !dto.getColor().equals("")) {
                sb.append("AND color = :color ");
                params.put("color", dto.getColor());
            }
        }

        return getResultList(sb.toString(), FILTER_RESULT_FOR_BATCH3, params);
    }
}

package com.crawl.api.repository.CustomRepository;

import com.crawl.api.common.Contains;
import com.crawl.api.common.StringUtils;
import com.crawl.api.dto.RequestFilterUrlBatch2Dto;
import com.crawl.api.dto.ResponseBatch2ResultDto;
import com.crawl.api.model.Batch2CrawlResultModel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class Batch2CustomRepository extends BaseRepositoryCustom{
    public final static String FILTER_URL_FOR_BATCH2 = "FILTER_URL_FOR_BATCH3";

    public void filterUrlForBatch3(RequestFilterUrlBatch2Dto dto) {
        StringBuilder sb = new StringBuilder();
        Map<String, Object> params = new HashMap<String, Object>();

        sb.append("INSERT INTO tbl_batch3_crawl_list(robot_id, execution_id, url, add_date, udp_date) (SELECT b2.robot_id, b2.execution_id, b2.url, NOW(), NOW() ")
                .append("FROM tbl_batch2_crawl_result AS b2 WHERE b2.robot_id = :robotId AND ");
        if (StringUtils.integerIsNull(dto.getExecutionId()) || dto.getExecutionId() == 0) {
            sb.append("execution_id = (SELECT MAX(execution_id) FROM tbl_batch2_crawl_result) ");
        } else {
            sb.append("execution_id = :executionId ");
            params.put("executionId", dto.getExecutionId());
        }
        sb.append("LIMIT :limit) ");
        params.put("robotId", dto.getRobotId());
        params.put("limit", Contains.Batch1.LIMIT_URL_FOR_BATCH_2);
        System.out.println(sb.toString());
        Integer obj = getSelectResult(sb.toString(), FILTER_URL_FOR_BATCH2, params);
        return;
    }

    public List<Batch2CrawlResultModel> getBatch2ResultFilter(ResponseBatch2ResultDto dto) {
        StringBuilder sb = new StringBuilder();
        Map<String, Object> params = new HashMap<String, Object>();

        sb.append("SELECT Id AS id, execution_id AS executionId, robot_id AS robotId, url, add_date AS addDate, " +
                "upd_date AS updDate, product_name AS productName, product_key AS productKey, cpu, ram, `storage`, vga, " +
                "monitor, `status`, original_price AS originalPrice, price, operating_system AS operatingSystem, color, " +
                "category_name AS categoryName FROM tbl_batch2_crawl_result ");
        if (dto != null) {
            sb.append("WHERE 1 ");
            if (dto.getRobotId() != null && !dto.getRobotId().equals("")) {
                sb.append("AND robot_id = :robotId ");
                params.put("robotId", dto.getRobotId());
            }
            if (dto.getCategoryName() != null && !dto.getCategoryName().equals("")) {
                sb.append("AND category_name = :categoryName ");
                params.put("categoryName", dto.getCategoryName());
            }
            if (dto.getProductName() != null && !dto.getProductName().equals("")) {
                sb.append("AND product_name = :productName ");
                params.put("productName", dto.getProductName());
            }
            if (dto.getColor() != null && !dto.getColor().equals("")) {
                sb.append("AND color = :color ");
                params.put("color", dto.getColor());
            }
        }
        System.out.println(sb.toString());
        return getResultList(sb.toString(), "FILTER_RESULT_FOR_BATCH2", params);
    }

    public List<Batch2CrawlResultModel> getBatch2ResultFilterString(String str) {
        StringBuilder sb = new StringBuilder();
        Map<String, Object> params = new HashMap<String, Object>();

        sb.append("SELECT * FROM tbl_batch2_crawl_result ");
        if (str != null && !str.equals("")) {
            sb.append("WHERE 1 ");
            String[] strList = str.split(",");
            System.out.println(str + "   str list length   " + strList.length);
            if (strList.length >= 2 && !strList[2].isEmpty()) {
                sb.append("AND robot_id = :robotId ");
                params.put("robotId", strList[2]);
            }
            if (strList.length >= 19 && !strList[19].isEmpty()) {
                sb.append("AND category_name = :categoryName ");
                params.put("categoryName", strList[19]);
            }
            if (strList.length >= 7 && !strList[7].isEmpty()) {
                sb.append("AND product_name = :productName ");
                params.put("productName", strList[7]);
            }
            if (strList.length >= 18 && !strList[18].isEmpty()) {
                sb.append("AND color = :color ");
                params.put("color", strList[18]);
            }
        }
        System.out.println(sb.toString());
        return getResultList(sb.toString(), "", params);
    }
}

package com.crawl.api.repository.CustomRepository;

import com.crawl.api.common.Contains;
import com.crawl.api.common.StringUtils;
import com.crawl.api.dto.RequestFilterUrlBatch2CheckboxDto;
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
    public final static String FILTER_RESULT_FOR_BATCH2 = "FILTER_RESULT_FOR_BATCH2";

    public void filterUrlForBatch3(RequestFilterUrlBatch2Dto dto) {
        StringBuilder sb = new StringBuilder();
        Map<String, Object> params = new HashMap<String, Object>();

        sb.append("INSERT INTO tbl_batch3_crawl_list(robot_id, execution_id, url, add_date, upd_date) (SELECT b2.robot_id, b2.execution_id, b2.url, NOW(), NOW() ")
                .append("FROM tbl_batch2_crawl_result AS b2 WHERE b2.robot_id = :robotId AND ");
        if (StringUtils.integerIsNull(dto.getExecutionId()) || dto.getExecutionId() == 0) {
            sb.append("execution_id = (SELECT MAX(execution_id) FROM tbl_batch2_crawl_result WHERE robot_id = :robotId) ");
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

    public void filterUrlForBatch3Checkbox(RequestFilterUrlBatch2CheckboxDto dto) {
        StringBuilder sb = new StringBuilder();
        Map<String, Object> params = new HashMap<String, Object>();

        sb.append("INSERT IGNORE INTO tbl_batch3_crawl_list(robot_id, execution_id, url, add_date, upd_date) (SELECT :robotName, b2.execution_id, b2.url, NOW(), NOW() ")
                .append("FROM tbl_batch2_crawl_result AS b2 WHERE b2.robot_id = :robotId AND ");
        if (StringUtils.integerIsNull(dto.getExecutionId()) || dto.getExecutionId() == 0) {
            sb.append("execution_id = (SELECT MAX(execution_id) FROM tbl_batch2_crawl_result WHERE robot_id = :robotId) ");
        } else {
            sb.append("execution_id = :executionId ");
            params.put("executionId", dto.getExecutionId());
        }
        if (dto.getRobotId() != null && !dto.getRobotId().isEmpty()) {
            params.put("robotId", dto.getRobotId());
        }
        if (dto.getCategoryName() != null && !dto.getCategoryName().isEmpty()) {
            sb.append("AND category_name = :categoryName ");
            params.put("categoryName", dto.getCategoryName());
        }
        if (dto.getProductName() != null && !dto.getProductName().isEmpty()) {
            sb.append("AND product_name LIKE :productName ");
            params.put("productName", "%" + dto.getProductName() + "%");
        }
        if (dto.getColor() != null && !dto.getColor().isEmpty()) {
            sb.append("AND color = :color ");
            params.put("color", dto.getColor());
        }
//        if (dto.getAddDateStart() != null && !dto.getAddDateStart().isEmpty()
//                && dto.getAddDateStart().matches(Contains.RegexString.DATE_TIME)) {
//            sb.append(" AND DATEDIFF(add_date, :addDateStart) >= 0 ");
//            params.put("addDateStart", dto.getAddDateStart());
//        }
//        if (dto.getAddDateEnd() != null && !dto.getAddDateEnd().isEmpty()
//                && dto.getAddDateEnd().matches(Contains.RegexString.DATE_TIME)) {
//            sb.append(" AND DATEDIFF(add_date, :addDateEnd) <= 0 ");
//            params.put("addDateEnd", dto.getAddDateEnd());
//        }
//        if (dto.getUpdDateStart() != null && !dto.getUpdDateStart().isEmpty()
//                && dto.getUpdDateStart().matches(Contains.RegexString.DATE_TIME)) {
//            sb.append(" AND DATEDIFF(add_date, :updDateStart) >= 0 ");
//            params.put("updDateStart", dto.getUpdDateStart());
//        }
//        if (dto.getUpdDateEnd() != null && !dto.getUpdDateEnd().isEmpty()
//                && dto.getUpdDateEnd().matches(Contains.RegexString.DATE_TIME)) {
//            sb.append(" AND DATEDIFF(add_date, :updDateEnd) <= 0 ");
//            params.put("updDateEnd", dto.getUpdDateEnd());
//        }
        String robotName = "HACOM03";
        if (dto.getRobotId().equals("HACOM02")) {
            robotName = "HACOM03";
        } else if (dto.getRobotId().equals("NCPC02")) {
            robotName = "NCPC03";
        }
        params.put("robotName", robotName);
        if (dto.getOriginalPriceMin() != null && !dto.getOriginalPriceMin().isEmpty()) {
            double money = Contains.ConvertString.getStringToMoney(dto.getOriginalPriceMin());
            if (money > -1) {
                sb.append(" AND CONVERT(REPLACE(REPLACE(original_price,'đ',''),'.',''), DECIMAL(20,3)) >= :originalPriceMin ");
                params.put("originalPriceMin", money);
            }
        }
        if (dto.getOriginalPriceMax() != null && !dto.getOriginalPriceMax().isEmpty()) {
            double money = Contains.ConvertString.getStringToMoney(dto.getOriginalPriceMax());
            if (money > -1) {
                sb.append(" AND CONVERT(REPLACE(REPLACE(original_price, 'đ',''),'.',''), DECIMAL(20,3)) <= :originalPriceMax ");
                params.put("originalPriceMax", money);
            }
        }
        if (dto.getPriceMin() != null && !dto.getPriceMin().isEmpty()) {
            double money = Contains.ConvertString.getStringToMoney(dto.getPriceMin());
            if (money > -1) {
                sb.append(" AND CONVERT(REPLACE(REPLACE(price,'đ',''),'.',''), DECIMAL(20,3)) >= :priceMin ");
                params.put("priceMin", money);
            }
        }
        if (dto.getPriceMax() != null && !dto.getPriceMax().isEmpty()) {
            double money = Contains.ConvertString.getStringToMoney(dto.getPriceMax());
            if (money > -1) {
                sb.append(" AND CONVERT(REPLACE(REPLACE(price, 'đ',''),'.',''), DECIMAL(20,3)) <= :priceMax ");
                params.put("priceMax", money);
            }
        }
        sb.append("LIMIT :limit) ");
        params.put("robotId", dto.getRobotId());
        params.put("limit", Contains.Batch1.LIMIT_URL_FOR_BATCH_2);
        System.out.println(sb.toString());
        System.out.println(params.toString());
        Integer obj = getSelectResult(sb.toString(), FILTER_URL_FOR_BATCH2, params);
        return;
    }

    public List<Batch2CrawlResultModel> getBatch2ResultFilter(ResponseBatch2ResultDto dto) {
        StringBuilder sb = new StringBuilder();
        Map<String, Object> params = new HashMap<String, Object>();

        sb.append("SELECT Id AS id, execution_id AS executionId, robot_id AS robotId, url, add_date AS addDate, " +
                "upd_date AS updDate, product_name AS productName, product_key AS productKey, cpu, ram, `storage`, vga, " +
                "monitor, `status`, original_price AS originalPrice, price, operating_system AS operatingSystem, color " +
                " FROM tbl_batch2_crawl_result ");
        if (dto != null) {
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
        System.out.println(sb.toString());
        return getResultList(sb.toString(), FILTER_RESULT_FOR_BATCH2, params);
    }
}

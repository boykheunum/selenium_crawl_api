package com.crawl.api.repository.CustomRepository;

import com.crawl.api.common.Contains;
import com.crawl.api.common.StringUtils;
import com.crawl.api.dto.RequestFilterUrlBatch1CheckboxDto;
import com.crawl.api.dto.RequestFilterUrlBatch1Dto;
import com.crawl.api.model.Batch1CrawlResultModel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class Batch1CustomRepository extends BaseRepositoryCustom {
    public final static String FILTER_URL_FOR_BATCH2 = "FILTER_URL_FOR_BATCH2";
    public final static String FILTER_RESULT_FOR_BATCH1 = "FILTER_RESULT_FOR_BATCH1";

    public void filterUrlForBatch2(RequestFilterUrlBatch1Dto dto) {
        StringBuilder sb = new StringBuilder();
        Map<String, Object> params = new HashMap<String, Object>();
        sb.append("INSERT INTO tbl_batch2_crawl_url(robot_id, execution_id, url) ");
        if (dto.getRobotId().equals("NCPC")) {
            sb.append("(SELECT 'NCPC02' AS 'robot_id', execution_id AS executionId, ");
        } else {
            sb.append("(SELECT 'HACOM02' AS 'robot_id', execution_id AS executionId, ");
        }
        sb.append("url AS 'url' FROM tbl_batch1_crawl_result AS b1 WHERE robot_id = :robotId AND ");
        if (StringUtils.integerIsNull(dto.getExecutionId()) || dto.getExecutionId() == 0) {
            sb.append("execution_id = (SELECT MAX(execution_id) FROM tbl_batch1_crawl_result WHERE robot_id = :robotId) ");
        } else {
            sb.append("execution_id = :executionId ");
            params.put("executionId", dto.getExecutionId());
        }
        sb.append("LIMIT :limit)");
        params.put("robotId", dto.getRobotId());
        params.put("limit", Contains.Batch1.LIMIT_URL_FOR_BATCH_2);
        System.out.println(sb.toString());
        Integer obj = getSelectResult(sb.toString(), FILTER_URL_FOR_BATCH2, params);
        return;
    }

    public void filterUrlForBatch2Checkbox(RequestFilterUrlBatch1CheckboxDto dto) {
        StringBuilder sb = new StringBuilder();
        Map<String, Object> params = new HashMap<String, Object>();
        sb.append("INSERT INTO tbl_batch2_crawl_url(robot_id, execution_id, url) ");
        if (dto.getRobotId().equals("NCPC")) {
            sb.append("(SELECT 'NCPC02' AS 'robot_id', execution_id AS executionId, ");
        } else {
            sb.append("(SELECT 'HACOM02' AS 'robot_id', execution_id AS executionId, ");
        }
        sb.append("url AS 'url' FROM tbl_batch1_crawl_result AS b1 WHERE robot_id = :robotId AND ");
        if (StringUtils.integerIsNull(dto.getExecutionId()) || dto.getExecutionId() == 0) {
            sb.append("execution_id = (SELECT MAX(execution_id) FROM tbl_batch1_crawl_result WHERE robot_id = :robotId) ");
        } else {
            sb.append("execution_id = :executionId ");
            params.put("executionId", dto.getExecutionId());
        }
        if (dto.getCategoryName() != null && !dto.getCategoryName().isEmpty()) {
            sb.append(" AND category_name = :categoryName ");
            params.put("categoryName", dto.getCategoryName());
        }
        if (dto.getAddDateStart() != null && !dto.getAddDateStart().isEmpty()
                && dto.getAddDateStart().matches(Contains.RegexString.DATE_TIME)) {
            sb.append(" AND DATEDIFF(add_date, :addDateStart) >= 0 ");
            params.put("addDateStart", dto.getAddDateStart());
        }
        if (dto.getAddDateEnd() != null && !dto.getAddDateEnd().isEmpty()
                && dto.getAddDateEnd().matches(Contains.RegexString.DATE_TIME)) {
            sb.append(" AND DATEDIFF(add_date, :addDateEnd) <= 0 ");
            params.put("addDateEnd", dto.getAddDateEnd());
        }
        if (dto.getUpdDateStart() != null && !dto.getUpdDateStart().isEmpty()
                && dto.getUpdDateStart().matches(Contains.RegexString.DATE_TIME)) {
            sb.append(" AND DATEDIFF(add_date, :updDateStart) >= 0 ");
            params.put("updDateStart", dto.getUpdDateStart());
        }
        if (dto.getUpdDateEnd() != null && !dto.getUpdDateEnd().isEmpty()
                && dto.getUpdDateEnd().matches(Contains.RegexString.DATE_TIME)) {
            sb.append(" AND DATEDIFF(add_date, :updDateEnd) <= 0 ");
            params.put("updDateEnd", dto.getUpdDateEnd());
        }
        sb.append("LIMIT :limit)");
        params.put("robotId", dto.getRobotId());
        params.put("limit", Contains.Batch1.LIMIT_URL_FOR_BATCH_2);
        System.out.println(sb.toString());
        Integer obj = getSelectResult(sb.toString(), FILTER_URL_FOR_BATCH2, params);
        return;
    }

    public List<Batch1CrawlResultModel> getBatch1ResultCheckbox(RequestFilterUrlBatch1CheckboxDto dto) {
        StringBuilder sb = new StringBuilder();
        Map<String, Object> params = new HashMap<String, Object>();
        sb.append("SELECT robot_id AS robotId, category_name AS categoryName, execution_id AS executionId, url, " +
                "add_date AS addDate, upd_date AS updDate FROM tbl_batch1_crawl_result ");
        if (dto != null) {
            sb.append(" WHERE 1 ");
            if (dto.getRobotId() != null && !dto.getRobotId().equals("")) {
                sb.append("AND robot_id = :robotId ");
                params.put("robotId", dto.getRobotId());
            }
            if (!StringUtils.integerIsNull(dto.getExecutionId()) && dto.getExecutionId() > 0) {
                sb.append("AND execution_id = :executionId ");
                params.put("executionId", dto.getExecutionId());
            }
            if (dto.getCategoryName() != null && !dto.getCategoryName().isEmpty()) {
                sb.append(" AND category_name = :categoryName ");
                params.put("categoryName", dto.getCategoryName());
            }
            if (dto.getAddDateStart() != null && !dto.getAddDateStart().isEmpty()
                    && dto.getAddDateStart().matches(Contains.RegexString.DATE_TIME)) {
                sb.append(" AND DATEDIFF(add_date, :addDateStart) >= 0 ");
                params.put("addDateStart", dto.getAddDateStart());
            }
            if (dto.getAddDateEnd() != null && !dto.getAddDateEnd().isEmpty()
                    && dto.getAddDateEnd().matches(Contains.RegexString.DATE_TIME)) {
                sb.append(" AND DATEDIFF(add_date, :addDateEnd) <= 0 ");
                params.put("addDateEnd", dto.getAddDateEnd());
            }
            if (dto.getUpdDateStart() != null && !dto.getUpdDateStart().isEmpty()
                    && dto.getUpdDateStart().matches(Contains.RegexString.DATE_TIME)) {
                sb.append(" AND DATEDIFF(add_date, :updDateStart) >= 0 ");
                params.put("updDateStart", dto.getUpdDateStart());
            }
            if (dto.getUpdDateEnd() != null && !dto.getUpdDateEnd().isEmpty()
                    && dto.getUpdDateEnd().matches(Contains.RegexString.DATE_TIME)) {
                sb.append(" AND DATEDIFF(add_date, :updDateEnd) <= 0 ");
                params.put("updDateEnd", dto.getUpdDateEnd());
            }
        }
        System.out.println(sb.toString());
        return getResultList(sb.toString(), FILTER_RESULT_FOR_BATCH1, params);
    }
}

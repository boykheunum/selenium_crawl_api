package com.crawl.api.repository.CustomRepository;

import com.crawl.api.dto.RequestFilterUrlBatch1Dto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.Map;

@Repository
public class Batch1CustomRepository extends BaseRepositoryCustom{
    @PersistenceContext
    EntityManager entityManager;
    public final static String FILTER_URL_FOR_BATCH2 = "FILTER_URL_FOR_BATCH2";

    public void filterUrlForBatch2(RequestFilterUrlBatch1Dto dto) {
        StringBuilder sb = new StringBuilder();
        Map<String, Object> params = new HashMap<String, Object>();
        getResultSingle(sb.toString(), FILTER_URL_FOR_BATCH2, params);
        return;
    }
}

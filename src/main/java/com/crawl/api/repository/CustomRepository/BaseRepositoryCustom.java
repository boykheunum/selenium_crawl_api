package com.crawl.api.repository.CustomRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseRepositoryCustom {
    @PersistenceContext
    EntityManager entityManager;

    public <T> Page<T> getResultPage(String sql, String resultSetMappingName,
                                     Map<String, Object> parameters, Pageable pageable) {

        try {
            Map<String, Object> queryParams = new HashMap<>();
            queryParams.putAll(parameters);
            queryParams.put("limit", pageable.getPageSize());
            queryParams.put("offset", pageable.getOffset());
            Query query = createQuery(entityManager, sql + " LIMIT :limit OFFSET :offset",
                    resultSetMappingName, queryParams);
            Query count = createQuery(entityManager, "SELECT COUNT(0) FROM ( " + sql + " ) AS c",
                    "", parameters);
            BigInteger total = (BigInteger) count.getSingleResult();
            return new PageImpl<T>(query.getResultList(), pageable,
                    total != null ? total.longValue() : 0);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    public <T> List<T> getResultList(String sql, String resultSetMappingName,
                                     Map<String, Object> parameters) {

        try {
            Query query = createQuery(entityManager, sql, resultSetMappingName, parameters);
            return query.getResultList();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    public <T> T getResultSingle(String sql, String resultSetMappingName,
                                 Map<String, Object> parameters) {

        try {
            Query query = createQuery(entityManager, sql, resultSetMappingName, parameters);
            return (T) query.getSingleResult();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    private Query createQuery(EntityManager entityManager, String sql, String resultSetMappingName,
                              Map<String, Object> parameters) {
        Query query;

        if (resultSetMappingName.isEmpty()) {
            query = entityManager.createNativeQuery(sql);
        } else {
            query = entityManager.createNativeQuery(sql, resultSetMappingName);
        }

        if (parameters == null) {
            return query;
        }

        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return query;
    }
}

package com.github.aurifolia.jpa.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

/**
 * where {attributeName} > {value}
 *
 * @author Peng Dan
 * @since 1.0
 */
@RequiredArgsConstructor
public class GreaterThanSpecification<T> implements Specification<T> {
    private final String attributeName;
    private final transient Comparable<Object> value;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.greaterThan(root.get(attributeName), value);
    }
}

package com.github.aurifolia.jpa.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

/**
 * where {attributeName} between {lower} and {upper}
 *
 * @author Peng Dan
 * @since 1.0
 */
@RequiredArgsConstructor
public class BetweenSpecification<T> implements Specification<T> {
    private final String attributeName;
    private final Comparable<Object> lower;
    private final Comparable<Object> upper;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.between(root.get(attributeName), lower, upper);
    }
}

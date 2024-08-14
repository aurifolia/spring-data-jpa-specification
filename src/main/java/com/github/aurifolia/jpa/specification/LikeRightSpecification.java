package com.github.aurifolia.jpa.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

/**
 * where {attributeName} like '{keyword}%'
 *
 * @author Peng Dan
 * @since 1.0
 */
@RequiredArgsConstructor
public class LikeRightSpecification<T> implements Specification<T> {
    private static final String LIKE_PATTERN = "%s%%";
    private final String attributeName;
    private final String keyword;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.like(root.get(attributeName), String.format(LIKE_PATTERN, keyword));
    }
}

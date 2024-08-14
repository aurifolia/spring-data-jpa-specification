package com.github.aurifolia.jpa.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

/**
 * where {attributeName} not like '%{keyword}%'
 *
 * @author Peng Dan
 * @since 1.0
 */
@RequiredArgsConstructor
public class NotLikeSpecification<T> implements Specification<T> {
    /**
     * pattern
     */
    private static final String LIKE_PATTERN = "%%%s%%";
    /**
     * attribute name
     */
    private final String attributeName;
    /**
     * keyword
     */
    private final String keyword;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.notLike(root.get(attributeName), String.format(LIKE_PATTERN, keyword));
    }
}

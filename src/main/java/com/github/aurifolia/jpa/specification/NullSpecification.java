package com.github.aurifolia.jpa.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

/**
 * where {attributeName} is null
 *
 * @author Peng Dan
 * @since 1.0
 */
@RequiredArgsConstructor
public class NullSpecification<T> implements Specification<T> {
    /**
     * attribute name
     */
    private final String attributeName;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.isNull(root.get(attributeName));
    }
}

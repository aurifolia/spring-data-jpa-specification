package com.github.aurifolia.jpa.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collection;

/**
 * where {attributeName} in (values[0], values[1], ...)
 *
 * @author Peng Dan
 * @since 1.0
 */
@RequiredArgsConstructor
public class InSpecification<T> implements Specification<T> {
    /**
     * attribute name
     */
    private final String attributeName;
    /**
     * values
     */
    private final Collection<?> values;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return root.get(attributeName).in(values);
    }
}

package io.github.aurifolia.jpa.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

/**
 * where {attributeName} &gt; {value}
 *
 * @author Peng Dan
 * @since 1.0
 */
@RequiredArgsConstructor
public class GreaterThanSpecification<T> implements Specification<T> {
    /**
     * attribute name
     */
    private final String attributeName;
    /**
     * value
     */
    private final transient Comparable<Object> value;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.greaterThan(root.get(attributeName), value);
    }
}

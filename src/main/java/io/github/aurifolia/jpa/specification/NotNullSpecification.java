package io.github.aurifolia.jpa.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

/**
 * where {attributeName} is not null
 *
 * @author Peng Dan
 * @since 1.0
 */
@RequiredArgsConstructor
public class NotNullSpecification<T> implements Specification<T> {
    /**
     * attribute name
     */
    private final String attributeName;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.isNotNull(root.get(attributeName));
    }
}

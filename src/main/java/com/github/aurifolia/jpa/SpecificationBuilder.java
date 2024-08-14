package com.github.aurifolia.jpa;

import com.github.aurifolia.jpa.specification.*;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import static jakarta.persistence.criteria.Predicate.BooleanOperator.AND;
import static jakarta.persistence.criteria.Predicate.BooleanOperator.OR;

/**
 * specification builder
 *
 * @author Peng Dan
 * @since 1.0
 */
public class SpecificationBuilder<T> {
    private final List<Specification<T>> specifications = new ArrayList<>();
    private final Predicate.BooleanOperator operator;

    public SpecificationBuilder() {
        this.operator = AND;
    }

    public SpecificationBuilder(Predicate.BooleanOperator operator) {
        this.operator = operator;
    }

    /**
     * If the condition is true, then the query statement is appended.
     * <p>where {attributeName} = {value}</p>
     *
     * @param condition     condition
     * @param attributeName attributeName
     * @param value         value
     * @return SpecificationBuilder
     */
    public SpecificationBuilder<T> equal(boolean condition, String attributeName, Object value) {
        if (condition) {
            specifications.add(new EqualSpecification<>(attributeName, value));
        }
        return this;
    }

    /**
     * If the condition is true, then the query statement is appended.
     * <p>where {attributeName} != {value}</p>
     *
     * @param condition     condition
     * @param attributeName attributeName
     * @param value         value
     * @return SpecificationBuilder
     */
    public SpecificationBuilder<T> notEqual(boolean condition, String attributeName, Object value) {
        if (condition) {
            specifications.add(new NotEqualSpecification<>(attributeName, value));
        }
        return this;
    }

    /**
     * If the condition is true, then the query statement is appended.
     * <p>where {attributeName} > {value}</p>
     *
     * @param condition     condition
     * @param attributeName attributeName
     * @param value         value
     * @return SpecificationBuilder
     */
    public SpecificationBuilder<T> greaterThan(boolean condition, String attributeName, Comparable<?> value) {
        if (condition) {
            //noinspection unchecked
            specifications.add(new GreaterThanSpecification<>(attributeName, (Comparable<Object>) value));
        }
        return this;
    }

    /**
     * If the condition is true, then the query statement is appended.
     * <p>where {attributeName} >= {value}</p>
     *
     * @param condition     condition
     * @param attributeName attributeName
     * @param value         value
     * @return SpecificationBuilder
     */
    public SpecificationBuilder<T> greaterThanOrEqual(boolean condition, String attributeName, Comparable<?> value) {
        if (condition) {
            //noinspection unchecked
            specifications.add(new GreaterThanOrEqualSpecification<>(attributeName, (Comparable<Object>) value));
        }
        return this;
    }

    /**
     * If the condition is true, then the query statement is appended.
     * <p>where {attributeName} < {value}</p>
     *
     * @param condition     condition
     * @param attributeName attributeName
     * @param value         value
     * @return SpecificationBuilder
     */
    public SpecificationBuilder<T> lessThan(boolean condition, String attributeName, Comparable<?> value) {
        if (condition) {
            //noinspection unchecked
            specifications.add(new LessThanSpecification<>(attributeName, (Comparable<Object>) value));
        }
        return this;
    }

    /**
     * If the condition is true, then the query statement is appended.
     * <p>where {attributeName} <= {value}</p>
     *
     * @param condition     condition
     * @param attributeName attributeName
     * @param value         value
     * @return SpecificationBuilder
     */
    public SpecificationBuilder<T> lessThanOrEqual(boolean condition, String attributeName, Comparable<?> value) {
        if (condition) {
            //noinspection unchecked
            specifications.add(new LessThanOrEqualSpecification<>(attributeName, (Comparable<Object>) value));
        }
        return this;
    }

    /**
     * If the condition is true, then the query statement is appended.
     * <p>where {attributeName} like '%{keyword}%'</p>
     *
     * @param condition     condition
     * @param attributeName attributeName
     * @param keyword       keyword
     * @return SpecificationBuilder
     */
    public SpecificationBuilder<T> like(boolean condition, String attributeName, String keyword) {
        if (condition) {
            specifications.add(new LikeSpecification<>(attributeName, keyword));
        }
        return this;
    }

    /**
     * If the condition is true, then the query statement is appended.
     * <p>where {attributeName} like '%{keyword}'</p>
     *
     * @param condition     condition
     * @param attributeName attributeName
     * @param keyword       keyword
     * @return SpecificationBuilder
     */
    public SpecificationBuilder<T> likeLeft(boolean condition, String attributeName, String keyword) {
        if (condition) {
            specifications.add(new LikeLeftSpecification<>(attributeName, keyword));
        }
        return this;
    }

    /**
     * If the condition is true, then the query statement is appended.
     * <p>where {attributeName} like '{keyword}%'</p>
     *
     * @param condition     condition
     * @param attributeName attributeName
     * @param keyword       keyword
     * @return SpecificationBuilder
     */
    public SpecificationBuilder<T> likeRight(boolean condition, String attributeName, String keyword) {
        if (condition) {
            specifications.add(new LikeRightSpecification<>(attributeName, keyword));
        }
        return this;
    }

    /**
     * If the condition is true, then the query statement is appended.
     * <p>where {attributeName} not like '%{keyword}%'</p>
     *
     * @param condition     condition
     * @param attributeName attributeName
     * @param keyword       keyword
     * @return SpecificationBuilder
     */
    public SpecificationBuilder<T> notLike(boolean condition, String attributeName, String keyword) {
        if (condition) {
            specifications.add(new NotLikeSpecification<>(attributeName, keyword));
        }
        return this;
    }

    /**
     * If the condition is true, then the query statement is appended.
     * <p>where {attributeName} not like '%{keyword}'</p>
     *
     * @param condition     condition
     * @param attributeName attributeName
     * @param keyword       keyword
     * @return SpecificationBuilder
     */
    public SpecificationBuilder<T> notLikeLeft(boolean condition, String attributeName, String keyword) {
        if (condition) {
            specifications.add(new NotLikeLeftSpecification<>(attributeName, keyword));
        }
        return this;
    }

    /**
     * If the condition is true, then the query statement is appended.
     * <p>where {attributeName} not like '{keyword}%'</p>
     *
     * @param condition     condition
     * @param attributeName attributeName
     * @param keyword       keyword
     * @return SpecificationBuilder
     */
    public SpecificationBuilder<T> notLikeRight(boolean condition, String attributeName, String keyword) {
        if (condition) {
            specifications.add(new NotLikeRightSpecification<>(attributeName, keyword));
        }
        return this;
    }

    /**
     * If the condition is true, then the query statement is appended.
     * <p>where {attributeName} in (values[0], values[1], ...)</p>
     *
     * @param condition     condition
     * @param attributeName attributeName
     * @param values        values
     * @return SpecificationBuilder
     */
    public SpecificationBuilder<T> in(boolean condition, String attributeName, Collection<?> values) {
        if (condition) {
            specifications.add(new InSpecification<>(attributeName, values));
        }
        return this;
    }

    /**
     * If the condition is true, then the query statement is appended.
     * <p>where {attributeName} not in (values[0], values[1], ...)</p>
     *
     * @param condition     condition
     * @param attributeName attributeName
     * @param values        values
     * @return SpecificationBuilder
     */
    public SpecificationBuilder<T> notIn(boolean condition, String attributeName, Collection<?> values) {
        if (condition) {
            specifications.add(new NotInSpecification<>(attributeName, values));
        }
        return this;
    }

    /**
     * If the condition is true, then the query statement is appended.
     * <p>where {attributeName} is null</p>
     *
     * @param condition     condition
     * @param attributeName attributeName
     * @return SpecificationBuilder
     */
    public SpecificationBuilder<T> isNull(boolean condition, String attributeName) {
        if (condition) {
            specifications.add(new NullSpecification<>(attributeName));
        }
        return this;
    }

    /**
     * If the condition is true, then the query statement is appended.
     * <p>where {attributeName} is not null</p>
     *
     * @param condition     condition
     * @param attributeName attributeName
     * @return SpecificationBuilder
     */
    public SpecificationBuilder<T> isNotNull(boolean condition, String attributeName) {
        if (condition) {
            specifications.add(new NotNullSpecification<>(attributeName));
        }
        return this;
    }

    /**
     * If the condition is true, then the query statement is appended.
     * <p>where {attributeName} between {lower} and {upper}</p>
     *
     * @param condition     condition
     * @param attributeName attributeNameName
     * @param lower         lower value
     * @param upper         upper value
     * @return SpecificationBuilder
     */
    public SpecificationBuilder<T> between(boolean condition, String attributeName, Comparable<?> lower, Comparable<?> upper) {
        if (condition) {
            //noinspection unchecked
            specifications.add(new BetweenSpecification<>(attributeName, (Comparable<Object>) lower, (Comparable<Object>) upper));
        }
        return this;
    }

    /**
     * a collection of AND
     *
     * @param function function
     * @return SpecificationBuilder
     */
    public SpecificationBuilder<T> and(Function<SpecificationBuilder<T>, SpecificationBuilder<T>> function) {
        specifications.add(function.apply(new SpecificationBuilder<>()).build());
        return this;
    }

    /**
     * a collection of OR
     *
     * @param function function
     * @return SpecificationBuilder
     */
    public SpecificationBuilder<T> or(Function<SpecificationBuilder<T>, SpecificationBuilder<T>> function) {
        specifications.add(function.apply(new SpecificationBuilder<>(OR)).build());
        return this;
    }

    /**
     * build Specification
     *
     * @return Specification
     */
    public Specification<T> build() {
        return operator == AND ? Specification.allOf(specifications) : Specification.anyOf(specifications);
    }
}

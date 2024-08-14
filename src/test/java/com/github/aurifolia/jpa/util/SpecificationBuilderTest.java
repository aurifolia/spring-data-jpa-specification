package com.github.aurifolia.jpa.util;

import com.github.aurifolia.jpa.SpecificationBuilder;
import com.github.aurifolia.jpa.entity.User;
import com.github.aurifolia.jpa.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * SpecificationBuilderTest
 *
 * @author Peng Dan
 * @since 1.0
 */
@SpringBootTest
class SpecificationBuilderTest {
    @Autowired
    private UserRepository repository;

    @BeforeEach
    void beforeEach() {
        repository.saveAndFlush(User.builder().id(1L).username("Ross").build());
        repository.saveAndFlush(User.builder().id(2L).username("Julie").build());
        repository.saveAndFlush(User.builder().id(3L).username("Gloria").build());
        repository.saveAndFlush(User.builder().id(4L).username("Carol").build());
    }

    @AfterEach
    void afterEach() {
        repository.deleteAll();
    }

    @Test
    void equal() {
        SpecificationBuilder<User> builder = new SpecificationBuilder<>();
        Specification<User> specification = builder.equal(true, "id", 1L)
                .equal(true, "username", "Ross")
                .build();
        List<User> users = repository.findAll(specification);
        assertEquals(users.size(), 1);

        builder = new SpecificationBuilder<>();
        specification = builder.equal(false, "id", 1L)
                .equal(true, "username", "Ross")
                .build();
        users = repository.findAll(specification);
        assertEquals(users.size(), 1);

        builder = new SpecificationBuilder<>();
        specification = builder.equal(false, "id", 1L)
                .equal(false, "username", "Ross")
                .build();
        users = repository.findAll(specification);
        assertEquals(users.size(), 4);
    }

    @Test
    void notEqual() {
        SpecificationBuilder<User> builder = new SpecificationBuilder<>();
        Specification<User> specification = builder.notEqual(true, "id", 1L).build();
        List<User> users = repository.findAll(specification);
        assertEquals(users.size(), 3);

        builder = new SpecificationBuilder<>();
        specification = builder.notEqual(false, "id", 1L).build();
        users = repository.findAll(specification);
        assertEquals(users.size(), 4);
    }

    @Test
    void greaterThan() {
        SpecificationBuilder<User> builder = new SpecificationBuilder<>();
        Specification<User> specification = builder.greaterThan(true, "id", 1L).build();
        List<User> users = repository.findAll(specification);
        assertEquals(users.size(), 3);

        builder = new SpecificationBuilder<>();
        specification = builder.greaterThan(false, "id", 1L).build();
        users = repository.findAll(specification);
        assertEquals(users.size(), 4);
    }

    @Test
    void greaterThanOrEqual() {
        SpecificationBuilder<User> builder = new SpecificationBuilder<>();
        Specification<User> specification = builder.greaterThanOrEqual(true, "id", 2L).build();
        List<User> users = repository.findAll(specification);
        assertEquals(users.size(), 3);

        builder = new SpecificationBuilder<>();
        specification = builder.greaterThanOrEqual(false, "id", 2L).build();
        users = repository.findAll(specification);
        assertEquals(users.size(), 4);
    }

    @Test
    void lessThan() {
        SpecificationBuilder<User> builder = new SpecificationBuilder<>();
        Specification<User> specification = builder.lessThan(true, "id", 3L).build();
        List<User> users = repository.findAll(specification);
        assertEquals(users.size(), 2);

        builder = new SpecificationBuilder<>();
        specification = builder.lessThan(false, "id", 3L).build();
        users = repository.findAll(specification);
        assertEquals(users.size(), 4);
    }

    @Test
    void lessThanOrEqual() {
        SpecificationBuilder<User> builder = new SpecificationBuilder<>();
        Specification<User> specification = builder.lessThanOrEqual(true, "id", 3L).build();
        List<User> users = repository.findAll(specification);
        assertEquals(users.size(), 3);

        builder = new SpecificationBuilder<>();
        specification = builder.lessThanOrEqual(false, "id", 3L).build();
        users = repository.findAll(specification);
        assertEquals(users.size(), 4);
    }

    @Test
    void like() {
        SpecificationBuilder<User> builder = new SpecificationBuilder<>();
        Specification<User> specification = builder.like(true, "username", "r").build();
        List<User> users = repository.findAll(specification);
        assertEquals(users.size(), 2);

        builder = new SpecificationBuilder<>();
        specification = builder.like(false, "username", "r").build();
        users = repository.findAll(specification);
        assertEquals(users.size(), 4);
    }

    @Test
    void likeLeft() {
        SpecificationBuilder<User> builder = new SpecificationBuilder<>();
        Specification<User> specification = builder.likeLeft(true, "username", "s").build();
        List<User> users = repository.findAll(specification);
        assertEquals(users.size(), 1);

        builder = new SpecificationBuilder<>();
        specification = builder.likeLeft(false, "username", "s").build();
        users = repository.findAll(specification);
        assertEquals(users.size(), 4);
    }

    @Test
    void likeRight() {
        SpecificationBuilder<User> builder = new SpecificationBuilder<>();
        Specification<User> specification = builder.likeRight(true, "username", "R").build();
        List<User> users = repository.findAll(specification);
        assertEquals(users.size(), 1);

        builder = new SpecificationBuilder<>();
        specification = builder.likeRight(false, "username", "R").build();
        users = repository.findAll(specification);
        assertEquals(users.size(), 4);
    }

    @Test
    void notLike() {
        SpecificationBuilder<User> builder = new SpecificationBuilder<>();
        Specification<User> specification = builder.notLike(true, "username", "s").build();
        List<User> users = repository.findAll(specification);
        assertEquals(users.size(), 3);

        builder = new SpecificationBuilder<>();
        specification = builder.notLike(false, "username", "s").build();
        users = repository.findAll(specification);
        assertEquals(users.size(), 4);
    }

    @Test
    void notLikeLeft() {
        SpecificationBuilder<User> builder = new SpecificationBuilder<>();
        Specification<User> specification = builder.notLikeLeft(true, "username", "s").build();
        List<User> users = repository.findAll(specification);
        assertEquals(users.size(), 3);

        builder = new SpecificationBuilder<>();
        specification = builder.notLikeLeft(false, "username", "s").build();
        users = repository.findAll(specification);
        assertEquals(users.size(), 4);
    }

    @Test
    void notLikeRight() {
        SpecificationBuilder<User> builder = new SpecificationBuilder<>();
        Specification<User> specification = builder.notLikeRight(true, "username", "R").build();
        List<User> users = repository.findAll(specification);
        assertEquals(users.size(), 3);

        builder = new SpecificationBuilder<>();
        specification = builder.notLikeRight(false, "username", "R").build();
        users = repository.findAll(specification);
        assertEquals(users.size(), 4);
    }

    @Test
    void in() {
        SpecificationBuilder<User> builder = new SpecificationBuilder<>();
        Specification<User> specification = builder.in(true, "id", Arrays.asList(1L, 2L)).build();
        List<User> users = repository.findAll(specification);
        assertEquals(users.size(), 2);

        builder = new SpecificationBuilder<>();
        specification = builder.in(false, "id", Arrays.asList(1L, 2L)).build();
        users = repository.findAll(specification);
        assertEquals(users.size(), 4);
    }

    @Test
    void notIn() {
        SpecificationBuilder<User> builder = new SpecificationBuilder<>();
        Specification<User> specification = builder.notIn(true, "id", Arrays.asList(1L, 2L)).build();
        List<User> users = repository.findAll(specification);
        assertEquals(users.size(), 2);

        builder = new SpecificationBuilder<>();
        specification = builder.notIn(false, "id", Arrays.asList(1L, 2L)).build();
        users = repository.findAll(specification);
        assertEquals(users.size(), 4);
    }

    @Test
    void isNull() {
        SpecificationBuilder<User> builder = new SpecificationBuilder<>();
        Specification<User> specification = builder.isNull(true, "id").build();
        List<User> users = repository.findAll(specification);
        assertEquals(users.size(), 0);

        builder = new SpecificationBuilder<>();
        specification = builder.isNull(false, "id").build();
        users = repository.findAll(specification);
        assertEquals(users.size(), 4);
    }

    @Test
    void isNotNull() {
        SpecificationBuilder<User> builder = new SpecificationBuilder<>();
        Specification<User> specification = builder.isNotNull(true, "id").build();
        List<User> users = repository.findAll(specification);
        assertEquals(users.size(), 4);

        builder = new SpecificationBuilder<>();
        specification = builder.isNotNull(false, "id").build();
        users = repository.findAll(specification);
        assertEquals(users.size(), 4);
    }

    @Test
    void between() {
        SpecificationBuilder<User> builder = new SpecificationBuilder<>();
        Specification<User> specification = builder.between(true, "id", 2, 3).build();
        List<User> users = repository.findAll(specification);
        assertEquals(users.size(), 2);

        builder = new SpecificationBuilder<>();
        specification = builder.between(false, "id", 2, 3).build();
        users = repository.findAll(specification);
        assertEquals(users.size(), 4);
    }

    @Test
    void and() {
        SpecificationBuilder<User> builder = new SpecificationBuilder<>();
        Specification<User> specification = builder.and(b -> b.equal(true, "id", 1L)
                .equal(true, "username", "Ross")).build();
        List<User> users = repository.findAll(specification);
        assertEquals(users.size(), 1);
    }

    @Test
    void or() {
        SpecificationBuilder<User> builder = new SpecificationBuilder<>();
        Specification<User> specification = builder.or(b -> b.equal(true, "id", 1L)
                .equal(true, "id", 2L)).build();
        List<User> users = repository.findAll(specification);
        assertEquals(users.size(), 2);
    }
}
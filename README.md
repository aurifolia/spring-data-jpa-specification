### Description

This is a tool for Specification in spring-boot-starter-data-jpa that provides a quick way to construct dynamic query
conditions

### Example

#### prepare

```sql
create table user_info (
    id bigint auto_increment primary key,
    username varchar(32) not null,
    nickname varchar(32)
)
```

```java

@Data
@Entity
@Table(name = "user_info")
public class User {
    @Id
    private Long id;
    private String username;
    private String nickname;
}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class UserQuery {
    private Long id;
    private String username;
    private String nickname;
}
```

#### equal

Let's query for users whose username is Tom and whose nickname is Cat

```java
public List<User> query(UserQuery query) {
    SpecificationBuilder<User> builder = new SpecificationBuilder<>();
    Specification<User> specification = builder.equal(StringUtils.hasText(query.getUsername()), "username", query.getUsername())
            .equal(StringUtils.hasText(query.getNickname()), "nickname", query.getNickname())
            .build();
    return userRepository.findAll(specification);
}

public void function() {
    List<User> users = query(UserQuery.builder().username("Tom").nickname("Cat").build());
}
```

#### not equal

Let's query for users whose username is not Tom

```java
public List<User> query(UserQuery query) {
    SpecificationBuilder<User> builder = new SpecificationBuilder<>();
    Specification<User> specification = builder.notEqual(StringUtils.hasText(query.getUsername()), "username", query.getUsername()).build();
    return userRepository.findAll(specification);
}

public void function() {
    List<User> users = query(UserQuery.builder().username("Tom").build());
}
```

#### greater than

Let's query for users whose ID is greater than 20

```java
public List<User> query(UserQuery query) {
    SpecificationBuilder<User> builder = new SpecificationBuilder<>();
    Specification<User> specification = builder.greaterThan(true, "id", 20L).build();
    return userRepository.findAll(specification);
}
```

#### greater than or equal

Let's query for users whose ID is greater than or equal 20

```java
public List<User> query(UserQuery query) {
    SpecificationBuilder<User> builder = new SpecificationBuilder<>();
    Specification<User> specification = builder.greaterThanOrEqual(true, "id", 20L).build();
    return userRepository.findAll(specification);
}
```

#### less than

Let's query for users whose ID is less than 20

```java
public List<User> query(UserQuery query) {
    SpecificationBuilder<User> builder = new SpecificationBuilder<>();
    Specification<User> specification = builder.lessThan(true, "id", 20L).build();
    return userRepository.findAll(specification);
}
```

#### greater than or equal

Let's query for users whose ID is less than or equal 20

```java
public List<User> query(UserQuery query) {
    SpecificationBuilder<User> builder = new SpecificationBuilder<>();
    Specification<User> specification = builder.lessThanOrEqual(true, "id", 20L).build();
    return userRepository.findAll(specification);
}
```

#### like

Let's query for users whose username contains Tom

```java
public List<User> query(UserQuery query) {
    SpecificationBuilder<User> builder = new SpecificationBuilder<>();
    Specification<User> specification = builder.like(true, "username", "Tom").build();
    return userRepository.findAll(specification);
}
```

#### like left

Let's query for users whose username ends with Tom

```java
public List<User> query(UserQuery query) {
    SpecificationBuilder<User> builder = new SpecificationBuilder<>();
    Specification<User> specification = builder.likeLeft(true, "username", "Tom").build();
    return userRepository.findAll(specification);
}
```

#### like right

Let's query for users whose username starts with Tom

```java
public List<User> query(UserQuery query) {
    SpecificationBuilder<User> builder = new SpecificationBuilder<>();
    Specification<User> specification = builder.likeRight(true, "username", "Tom").build();
    return userRepository.findAll(specification);
}
```

#### not like

Let's query for users whose username doesn't include Tom

```java
public List<User> query(UserQuery query) {
    SpecificationBuilder<User> builder = new SpecificationBuilder<>();
    Specification<User> specification = builder.notLike(true, "username", "Tom").build();
    return userRepository.findAll(specification);
}
```

#### not like left

Let's query for users whose username doesn't end with Tom

```java
public List<User> query(UserQuery query) {
    SpecificationBuilder<User> builder = new SpecificationBuilder<>();
    Specification<User> specification = builder.notLikeLeft(true, "username", "Tom").build();
    return userRepository.findAll(specification);
}
```

#### not like right

Let's query for users whose username doesn't start with Tom

```java
public List<User> query(UserQuery query) {
    SpecificationBuilder<User> builder = new SpecificationBuilder<>();
    Specification<User> specification = builder.notLikeRight(true, "username", "Tom").build();
    return userRepository.findAll(specification);
}
```

#### in

select ... where username in ('Tom', 'Cat')

```java
public List<User> query(UserQuery query) {
    SpecificationBuilder<User> builder = new SpecificationBuilder<>();
    Specification<User> specification = builder.in(true, "username", Arrays.asList("Tom", "Cat")).build();
    return userRepository.findAll(specification);
}
```

#### not in

select ... where username not in ('Tom', 'Cat')

```java
public List<User> query(UserQuery query) {
    SpecificationBuilder<User> builder = new SpecificationBuilder<>();
    Specification<User> specification = builder.notIn(true, "username", Arrays.asList("Tom", "Cat")).build();
    return userRepository.findAll(specification);
}
```

#### is null

select ... where nickname is null

```java
public List<User> query(UserQuery query) {
    SpecificationBuilder<User> builder = new SpecificationBuilder<>();
    Specification<User> specification = builder.isNull(true, "nickname").build();
    return userRepository.findAll(specification);
}
```

#### is not null

select ... where nickname is not null

```java
public List<User> query(UserQuery query) {
    SpecificationBuilder<User> builder = new SpecificationBuilder<>();
    Specification<User> specification = builder.isNotNull(true, "nickname").build();
    return userRepository.findAll(specification);
}
```

#### between

select ... where id between 20 and 30

```java
public List<User> query(UserQuery query) {
    SpecificationBuilder<User> builder = new SpecificationBuilder<>();
    Specification<User> specification = builder.between(true, "id", 20L, 30L).build();
    return userRepository.findAll(specification);
}
```

#### or

select ... where id = 20 or id = 30

```java
public List<User> query(UserQuery query) {
    SpecificationBuilder<User> builder = new SpecificationBuilder<>();
    Specification<User> specification = builder.or(b -> b.equal(true, "id", 20L)
            .equal(true, "id", 30L)).build();
    return userRepository.findAll(specification);
}
```

#### complex query

select ... where (id = 20 or id = 30) and (username like '%Tom%' or username like '%Cat%')

```java
public List<User> query(UserQuery query) {
    SpecificationBuilder<User> builder = new SpecificationBuilder<>();
    Specification<User> specification = builder.or(b -> b.equal(true, "id", 20L)
                    .equal(true, "id", 30L))
            .or(b -> b.like(true, "username", "Tom")
                    .like(true, "username", "Cat"))
            .build();
    return userRepository.findAll(specification);
}
```
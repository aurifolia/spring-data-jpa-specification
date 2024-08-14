package io.github.aurifolia.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * user info
 *
 * @author Peng Dan
 * @since 1.0
 */
@Data
@Entity
@Table(name = "user_info")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    /**
     * user ID
     */
    @Id
    private Long id;
    /**
     * username
     */
    private String username;
    /**
     * nickname
     */
    private String nickname;
}

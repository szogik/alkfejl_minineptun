package hu.elte.backend.minineptun.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(mappedBy = "user")
    private BaseEntity entity;

    public enum Role implements GrantedAuthority {
        ROLE_STUDENT, ROLE_LECTURER, ROLE_ADMIN;

        @Override
        public String getAuthority() {
            return this.toString();
        }
    }
}

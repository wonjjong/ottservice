package wonjjong.dev.ottservice.domain.user;

import lombok.*;
import wonjjong.dev.ottservice.domain.BaseTimeEntity;

import javax.persistence.*;

@Entity(name="users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String password;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String email, String picture, Role role, String password) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
        this.password = password;
    }

    public User update(String name, String picture) {
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}

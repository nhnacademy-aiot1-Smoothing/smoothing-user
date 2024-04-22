package live.smoothing.user.userrole.entity;

import live.smoothing.user.role.entity.Role;
import live.smoothing.user.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Entity
@Table(name = "user_roles")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRole {

    @Id
    @Column(name = "user_role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userRoleId;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public UserRole(User user, Role role) {

        this.user = user;
        this.role = role;
    }
}

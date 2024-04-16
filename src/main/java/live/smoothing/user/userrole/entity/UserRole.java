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

    @EmbeddedId
    private Pk pk;

    @MapsId("roleId")
    @JoinColumn(name = "role_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Role role;

    @MapsId("userId")
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Getter
    @Embeddable
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Pk implements Serializable {

        private Long roleId;
        private String userId;
    }

    public UserRole(Role role, User user) {
        this.role = role;
        this.user = user;
        this.pk = new Pk(role.getRoleId(), user.getUserId());
    }
}

package live.smoothing.user.role.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@Entity
@DynamicUpdate
@Table(name = "roles")
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 여기서 protected
public class Role {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column(name = "role_info")
    private String roleInfo;

    public Role(String roleInfo) {
        this.roleInfo = roleInfo;
    }

    public void modifyRoleInfo(String roleInfo) {
        this.roleInfo = roleInfo;
    }
}

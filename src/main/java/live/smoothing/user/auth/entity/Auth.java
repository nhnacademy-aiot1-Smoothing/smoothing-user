package live.smoothing.user.auth.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@Entity
@DynamicUpdate
@Table(name = "auths")
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 여기서 protected
public class Auth {

    @Id
    @Column(name = "auth_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authId;

    @Column(name = "auth_info")
    private String authInfo;

    public Auth(String authInfo) {
        this.authInfo = authInfo;
    }

    public Auth(long l, String testAuth) {
    }

    public void updateAuthInfo(String authInfo) {
        this.authInfo = authInfo;
    }
}

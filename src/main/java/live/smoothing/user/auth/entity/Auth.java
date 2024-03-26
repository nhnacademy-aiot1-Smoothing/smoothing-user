package live.smoothing.user.auth.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "auths")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Auth {

    @Id
    @Column(name = "auth_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authId;

    @Column(name = "auth_info")
    private String authInfo;

    public Auth(Long authId, String authInfo) {
        this.authId = authId;
        this.authInfo = authInfo;
    }
}

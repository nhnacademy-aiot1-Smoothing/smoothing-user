package live.smoothing.user.userauth.entity;

import live.smoothing.user.auth.entity.Auth;
import live.smoothing.user.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Entity
@Table(name = "user_auths")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserAuth {

    @EmbeddedId
    private Pk pk;

    @MapsId("authId")
    @JoinColumn(name = "auth_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Auth auth;

    @MapsId("userId")
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Getter
    @Embeddable
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Pk implements Serializable {

        private Long authId;
        private String userId;
    }

    public UserAuth(Auth auth, User user) {
        this.auth = auth;
        this.user = user;
        this.pk = new Pk(auth.getAuthId(), user.getUserId());
    }
}

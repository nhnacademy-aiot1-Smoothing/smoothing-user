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

    @ManyToOne
    @MapsId("authId")
    private Auth auth;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @Getter
    @Embeddable
    @EqualsAndHashCode
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Pk implements Serializable {
        private Long authId;
        private String userId;
    }
}

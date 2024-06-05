package live.smoothing.user.oauth.entity;

import live.smoothing.user.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_oauths")
public class UserOAuth {

    @Id
    @Column(name = "user_oauth_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userOAuthId;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private OAuthProvider oAuthProvider;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "provider_key")
    private String providerKey;
}

package live.smoothing.user.oauth.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "oauth_providers")
public class OAuthProvider {

    @Id
    @Column(name = "provider_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer providerId;

    @Column(name = "provider_name")
    private String providerName;
}

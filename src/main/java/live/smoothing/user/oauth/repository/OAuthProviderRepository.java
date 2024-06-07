package live.smoothing.user.oauth.repository;

import live.smoothing.user.oauth.entity.OAuthProvider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OAuthProviderRepository extends JpaRepository<OAuthProvider, Integer> {

    OAuthProvider findByProviderName(String providerName);
}

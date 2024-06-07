package live.smoothing.user.oauth.repository;

import live.smoothing.user.oauth.entity.UserOAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserOAuthRepository extends JpaRepository<UserOAuth, Integer> {

    @Query("SELECT u.oAuthProvider.providerName FROM UserOAuth u WHERE u.user.userId = :userId")
    List<String> getProviderNamesByUserId(String userId);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM UserOAuth u WHERE u.providerKey = :providerKey AND u.oAuthProvider.providerName = :providerName")
    boolean findByProviderKey_providerName(String providerKey, String providerName);

    @Query("SELECT u.user.userId FROM UserOAuth u WHERE u.providerKey = :providerKey AND u.oAuthProvider.providerName = :providerName")
    String findUserIdByProviderKey_providerName(String providerKey, String providerName);

    @Modifying
    @Transactional
    @Query("DELETE FROM UserOAuth u WHERE u.user.userId = :userId AND u.oAuthProvider = (select o from OAuthProvider o where o.providerName = :providerName)")
    void deleteByUserUserIdAndOAuthProviderProviderName(String userId, String providerName);
}

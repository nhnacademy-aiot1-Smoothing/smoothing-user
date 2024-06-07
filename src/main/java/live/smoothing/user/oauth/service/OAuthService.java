package live.smoothing.user.oauth.service;

import live.smoothing.user.oauth.dto.UserIdDto;
import live.smoothing.user.oauth.dto.UserOAuthExistDto;
import live.smoothing.user.oauth.dto.UserOAuthListDto;
import live.smoothing.user.oauth.entity.UserOAuth;

import java.util.Optional;

public interface OAuthService {

    UserOAuthListDto getRegisteredOAuthList(String userId);

    UserOAuthExistDto getUserOAuthByProviderName(String providerKey, String providerId);

    void saveUserOAuth(String userId, String providerKey, String providerId);

    UserIdDto getUserIdByProviderKeyAndProviderName(String providerKey, String providerName);

    void deleteOAuth(String userId, String providerName);
}

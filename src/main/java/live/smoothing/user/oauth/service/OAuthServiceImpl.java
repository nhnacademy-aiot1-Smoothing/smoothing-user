package live.smoothing.user.oauth.service;

import live.smoothing.user.oauth.dto.UserIdDto;
import live.smoothing.user.oauth.dto.UserOAuthExistDto;
import live.smoothing.user.oauth.dto.UserOAuthListDto;
import live.smoothing.user.oauth.entity.UserOAuth;
import live.smoothing.user.oauth.repository.OAuthProviderRepository;
import live.smoothing.user.oauth.repository.UserOAuthRepository;
import live.smoothing.user.user.entity.User;
import live.smoothing.user.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service("oAuthService")
public class OAuthServiceImpl implements OAuthService{

    private final OAuthProviderRepository oAuthProviderRepository;
    private final UserOAuthRepository userOAuthRepository;
    private final UserRepository userRepository;

    @Override
    public UserOAuthListDto getRegisteredOAuthList(String userId) {
        return new UserOAuthListDto(userOAuthRepository.getProviderNamesByUserId(userId));
    }

    @Override
    public UserOAuthExistDto getUserOAuthByProviderName(String providerKey,String providerName) {
        return new UserOAuthExistDto(userOAuthRepository.findByProviderKey_providerName(providerKey,providerName));
    }

    @Override
    public void saveUserOAuth(String userId, String providerKey, String providerId) {
        userOAuthRepository.save(new UserOAuth(null,oAuthProviderRepository.findByProviderName(providerId), userRepository.getReferenceById(userId), providerKey));
    }

    @Override
    public UserIdDto getUserIdByProviderKeyAndProviderName(String providerKey, String providerName) {
        return new UserIdDto(userOAuthRepository.findUserIdByProviderKey_providerName(providerKey, providerName));
    }

    @Override
    public void deleteOAuth(String userId, String providerName) {
        userOAuthRepository.deleteByUserUserIdAndOAuthProviderProviderName(userId,providerName);
    }
}

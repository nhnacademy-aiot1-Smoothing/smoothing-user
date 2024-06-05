package live.smoothing.user.oauth.controller;

import live.smoothing.user.oauth.dto.UserIdDto;
import live.smoothing.user.oauth.dto.UserOAuthExistDto;
import live.smoothing.user.oauth.dto.UserOAuthListDto;
import live.smoothing.user.oauth.service.OAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/oauth")
public class OAuthController {

    private final OAuthService oAuthService;

    @GetMapping
    public UserOAuthListDto getRegisteredOAuthList(@RequestHeader("X-USER-ID") String userId) {
        return oAuthService.getRegisteredOAuthList(userId);
    }

    @GetMapping("/exist")
    public UserOAuthExistDto getUserOAuthByProviderKey(@PathParam("providerKey") String providerKey,
                                                       @PathParam("providerName") String providerName) {
        return oAuthService.getUserOAuthByProviderName(providerKey, providerName);
    }

    @PostMapping
    public void saveUserOAuth(@RequestHeader("X-USER-ID") String userId,
                              @PathParam("providerKey") String providerKey,
                              @PathParam("providerId") String providerId) {
        oAuthService.saveUserOAuth(userId, providerKey, providerId);
    }

    @GetMapping("/userId")
    public UserIdDto getUserIdByProviderKeyAndProviderName(@PathParam("providerKey") String providerKey,
                                                           @PathParam("providerName") String providerName) {
        return oAuthService.getUserIdByProviderKeyAndProviderName(providerKey, providerName);
    }

    @DeleteMapping
    public void deleteUserOAuth(@RequestHeader("X-USER-ID") String userId,
                                @PathParam("providerName") String providerName) {
        oAuthService.deleteOAuth(userId, providerName);
    }
}

package live.smoothing.user.hook.service;

import live.smoothing.user.hook.dto.request.UserHookCreateRequest;
import live.smoothing.user.hook.dto.request.UserHookUpdateRequest;
import live.smoothing.user.hook.dto.response.WebHookResponse;

import java.util.List;

public interface UserHookService {

    void createUserHook(String userId, UserHookCreateRequest request);
    void updateUserHook(String userId, Integer hookListId, UserHookUpdateRequest request);
    void deleteUserHook(String userId, Integer hookListId);
    List<WebHookResponse> getUserHookList(String userId);
    WebHookResponse getUserHook(String userId, Integer hookListId);
}

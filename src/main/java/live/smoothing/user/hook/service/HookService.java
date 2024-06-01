package live.smoothing.user.hook.service;

import live.smoothing.user.hook.dto.request.HookCreateRequest;
import live.smoothing.user.hook.dto.request.HookModifyRequest;
import live.smoothing.user.hook.dto.response.HookUrlResponse;
import live.smoothing.user.hook.dto.response.UserHookResponse;

import java.util.List;

public interface HookService {

    UserHookResponse getHook(String userId);

    void createHook(String userId, HookCreateRequest request);

    void modifyHook(String userId, HookModifyRequest request);

    void deleteHook(String userId);

    List<HookUrlResponse> getUsersHookList(Integer hookTypeId);
}

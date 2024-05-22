package live.smoothing.user.hook.repository;

import live.smoothing.user.hook.dto.response.HookUrlResponse;

import java.util.List;

public interface CustomHookRepository {

    List<HookUrlResponse> getUsersHookList(Integer hookTypeId);
}

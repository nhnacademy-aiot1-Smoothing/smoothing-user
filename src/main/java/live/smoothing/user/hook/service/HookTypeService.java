package live.smoothing.user.hook.service;

import live.smoothing.user.hook.dto.request.HookTypeCreateRequest;
import live.smoothing.user.hook.dto.request.HookTypeModifyRequest;
import live.smoothing.user.hook.entity.HookType;

import java.util.List;

public interface HookTypeService {

    HookType getHookType(Integer hookTypeId);
    List<HookType> getHookTypes();
    void createHookType(HookTypeCreateRequest request);
    void modifyHookType(Integer hookTypeId, HookTypeModifyRequest request);
    void deleteHookType(Integer hookTypeId);
}

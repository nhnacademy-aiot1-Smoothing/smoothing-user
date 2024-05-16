package live.smoothing.user.hook.service.impl;

import live.smoothing.user.advice.ErrorCode;
import live.smoothing.user.advice.exception.ServiceException;
import live.smoothing.user.hook.dto.request.HookCreateRequest;
import live.smoothing.user.hook.dto.request.HookModifyRequest;
import live.smoothing.user.hook.dto.response.HookUrlResponse;
import live.smoothing.user.hook.dto.response.UserHookResponse;
import live.smoothing.user.hook.entity.Hook;
import live.smoothing.user.hook.entity.HookType;
import live.smoothing.user.hook.repository.HookRepository;
import live.smoothing.user.hook.repository.HookTypeRepository;
import live.smoothing.user.hook.service.HookService;
import live.smoothing.user.user.entity.User;
import live.smoothing.user.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class HookServiceImpl implements HookService {

    private final UserRepository userRepository;
    private final HookRepository hookRepository;
    private final HookTypeRepository hookTypeRepository;

    @Override
    public UserHookResponse getHook(String userId) {

        Hook hook = hookRepository.findByUser_UserId(userId);

        if (Objects.isNull(hook)) {
            return new UserHookResponse(null, null, null);
        }

        return new UserHookResponse(hook.getHookType().getHookTypeId(), hook.getHookType().getHookTypeName(), hook.getHookUrl());
    }

    @Override
    public void createHook(String userId, HookCreateRequest request) {

        boolean exist = hookRepository.existsByUser_UserId(userId);

        if (exist) {
            throw new ServiceException(ErrorCode.HOOK_ALREADY_EXISTS);
        }

        User user = userRepository.findById(userId).orElseThrow(() -> new ServiceException(ErrorCode.USER_NOT_FOUND));
        HookType hookType = hookTypeRepository.findById(request.getHookTypeId()).orElseThrow(() -> new ServiceException(ErrorCode.HOOKTYPE_NOT_FOUND));

        Hook hook = new Hook(request.getHookUrl(), user, hookType);

        hookRepository.save(hook);
    }

    @Override
    public void modifyHook(String userId, HookModifyRequest request) {

        Hook hook = hookRepository.findByUser_UserId(userId);

        if (Objects.isNull(hook)) {
            throw new ServiceException(ErrorCode.HOOK_NOT_FOUND);
        }

        HookType hookType = hookTypeRepository.findById(request.getHookTypeId()).orElse(null);
        hook.modifyHookUrl(hookType, request.getHookUrl());

        hookRepository.save(hook);
    }

    @Override
    public void deleteHook(String userId) {

        Hook hook = hookRepository.findByUser_UserId(userId);

        if (Objects.isNull(hook)) {
            throw new ServiceException(ErrorCode.HOOK_NOT_FOUND);
        }

        hookRepository.delete(hook);
    }

    @Override
    public List<HookUrlResponse> getUsersHookList(Integer hookTypeId) {

        return hookRepository.getUsersHookList(hookTypeId);
    }
}

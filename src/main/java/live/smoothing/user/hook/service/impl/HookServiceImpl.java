package live.smoothing.user.hook.service.impl;

import live.smoothing.user.advice.ErrorCode;
import live.smoothing.user.advice.exception.ServiceException;
import live.smoothing.user.hook.dto.request.HookCreateRequest;
import live.smoothing.user.hook.dto.request.HookModifyRequest;
import live.smoothing.user.hook.entity.Hook;
import live.smoothing.user.hook.entity.HookType;
import live.smoothing.user.hook.repository.HookRepository;
import live.smoothing.user.hook.repository.HookTypeRepository;
import live.smoothing.user.hook.service.HookService;
import live.smoothing.user.user.entity.User;
import live.smoothing.user.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HookServiceImpl implements HookService {

    private UserRepository userRepository;
    private HookRepository hookRepository;
    private HookTypeRepository hookTypeRepository;

    @Override
    public Hook getHook(String userId) {

        return hookRepository.findByUser_UserId(userId);
    }

    @Override
    public void createHook(String userId, HookCreateRequest request) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ServiceException(ErrorCode.USER_NOT_FOUND));
        HookType hookType = hookTypeRepository.findById(request.getHookTypeId()).orElseThrow(() -> new ServiceException(ErrorCode.HOOKTYPE_NOT_FOUND));

        Hook hook = new Hook(request.getHookUrl(), user, hookType);

        hookRepository.save(hook);
    }

    @Override
    public void modifyHook(String userId, HookModifyRequest request) {

        Hook hook = hookRepository.findByUser_UserId(userId);
        hook.modifyHookUrl(request.getHookUrl());

        hookRepository.save(hook);
    }

    @Override
    public void deleteHook(String userId) {

        Hook hook = hookRepository.findByUser_UserId(userId);

        hookRepository.delete(hook);
    }
}

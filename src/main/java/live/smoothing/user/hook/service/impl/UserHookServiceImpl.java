package live.smoothing.user.hook.service.impl;

import live.smoothing.user.advice.ErrorCode;
import live.smoothing.user.advice.exception.ServiceException;
import live.smoothing.user.hook.dto.request.UserHookCreateRequest;
import live.smoothing.user.hook.dto.request.UserHookUpdateRequest;
import live.smoothing.user.hook.dto.response.WebHookResponse;
import live.smoothing.user.hook.entity.HookEventType;
import live.smoothing.user.hook.entity.HookType;
import live.smoothing.user.hook.entity.UserHook;
import live.smoothing.user.hook.entity.UserHookEventType;
import live.smoothing.user.hook.repository.HookEventTypeRepository;
import live.smoothing.user.hook.repository.HookTypeRepository;
import live.smoothing.user.hook.repository.UserHookEventTypeRepository;
import live.smoothing.user.hook.repository.UserHookRepository;
import live.smoothing.user.hook.service.UserHookService;
import live.smoothing.user.user.entity.User;
import live.smoothing.user.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class UserHookServiceImpl implements UserHookService {

    private final UserRepository userRepository;
    private final UserHookRepository userHookRepository;
    private final HookEventTypeRepository hookEventTypeRepository;
    private final HookTypeRepository hookTypeRepository;
    private final UserHookEventTypeRepository userHookEventTypeRepository;
    @Override
    public void createUserHook(String userId, UserHookCreateRequest request) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ServiceException(ErrorCode.USER_NOT_FOUND));

        HookType hookType = hookTypeRepository.findById(request.getHookTypeId()).orElse(null);

        UserHook userHook = new UserHook(request.getHookName(), hookType, user, request.getHookUrl());

        userHookRepository.save(userHook);

        for (Integer hookEventId : request.getHookEventIds()) {

            HookEventType hookEventType = hookEventTypeRepository.findById(hookEventId).orElse(null);
            UserHookEventType userHookEventType = new UserHookEventType(userHook, hookEventType);
            userHookEventTypeRepository.save(userHookEventType);
        }
    }

    @Override
    public void updateUserHook(String userId, Integer hookListId, UserHookUpdateRequest request) {

        UserHook userHook = userHookRepository.findByUser_UserIdAndHookListId(userId, hookListId);

        if (request.getHookName() != null) {
            Objects.requireNonNull(userHook).modifyHookName(request.getHookName());
        }
        if (request.getHookTypeId() != null) {
            HookType hookType = hookTypeRepository.findById(request.getHookTypeId()).orElse(null);
            Objects.requireNonNull(userHook).modifyHookType(hookType);
        }
        if (request.getHookUrl() != null) {
            Objects.requireNonNull(userHook).modifyHookUrl(request.getHookUrl());
        }

        userHookEventTypeRepository.deleteByUserHook(userHook);

        if (request.getHookEventIds() != null) {
            for (Integer hookEventId : request.getHookEventIds()) {
                HookEventType hookEventType = hookEventTypeRepository.findById(hookEventId).orElseThrow(null);
                UserHookEventType userHookEventType = new UserHookEventType(userHook, hookEventType);
                userHookEventTypeRepository.save(userHookEventType);
            }
        }

        userHookRepository.save(userHook);
    }

    @Override
    public void deleteUserHook(String userId, Integer hookListId) {

        UserHook userHook = userHookRepository.findByUser_UserIdAndHookListId(userId, hookListId);

        userHookEventTypeRepository.deleteByUserHook(userHook);
        userHookRepository.delete(userHook);
    }

    @Override
    public List<WebHookResponse> getUserHookList(String userId) {

        List<UserHook> userHooks = userHookRepository.findByUser_UserId(userId);

        List<WebHookResponse> webHookResponses = new ArrayList<>();

        for (UserHook hook : userHooks) {

            List<HookEventType> hookEventTypes = new ArrayList<>();

            for (UserHookEventType userHookEventType : userHookEventTypeRepository.findByUserHook(hook)) {
                hookEventTypes.add(userHookEventType.getHookEventType());
            }

            WebHookResponse webHookResponse = new WebHookResponse(hook.getHookName(), hook.getHookType(), hook.getHookUrl(), hookEventTypes);
            webHookResponses.add(webHookResponse);
        }
        return webHookResponses;
    }

    @Override
    public WebHookResponse getUserHook(String userId, Integer hookListId) {

        UserHook userHook = userHookRepository.findByUser_UserIdAndHookListId(userId, hookListId);

        if (Objects.isNull(userHook)) {
            throw new ServiceException(ErrorCode.HOOK_NOT_FOUND);
        }

        List<HookEventType> hookEventTypes = new ArrayList<>();

        for (UserHookEventType userHookEventType : userHookEventTypeRepository.findByUserHook(userHook)) {
            hookEventTypes.add(userHookEventType.getHookEventType());
        }

        return new WebHookResponse(userHook.getHookName(), userHook.getHookType(), userHook.getHookUrl(), hookEventTypes);
    }
}

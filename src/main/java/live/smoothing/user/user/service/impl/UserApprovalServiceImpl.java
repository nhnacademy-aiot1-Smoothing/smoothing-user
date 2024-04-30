package live.smoothing.user.user.service.impl;

import live.smoothing.user.advice.ErrorCode;
import live.smoothing.user.advice.exception.ServiceException;
import live.smoothing.user.user.dto.WaitingUser;
import live.smoothing.user.user.entity.User;
import live.smoothing.user.user.entity.UserState;
import live.smoothing.user.user.repository.UserRepository;
import live.smoothing.user.user.service.UserApprovalService;
import live.smoothing.user.userrole.dto.request.UserRoleCreateRequest;
import live.smoothing.user.userrole.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserApprovalServiceImpl implements UserApprovalService {

    private final UserRepository userRepository;
    private final UserRoleService userRoleService;
    @Override
    public Page<WaitingUser> waitingUserList(int page, int size) {

        return userRepository.findWaitingUsers(page, size);
    }

    @Override
    public void approve(UserRoleCreateRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ServiceException(ErrorCode.USER_NOT_FOUND));

        user.modifyUserState(UserState.APPROVED);
        userRepository.save(user);

        userRoleService.createUserRole(request);
    }

    @Override
    public void reject(String userId) {

        userRepository.deleteById(userId);
    }

}

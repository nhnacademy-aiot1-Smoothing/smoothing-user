package live.smoothing.user.user.service.impl;

import live.smoothing.user.user.dto.WaitingUser;
import live.smoothing.user.user.entity.User;
import live.smoothing.user.user.entity.UserState;
import live.smoothing.user.user.repository.UserRepository;
import live.smoothing.user.userrole.dto.request.UserRoleCreateRequest;
import live.smoothing.user.userrole.service.UserRoleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserApprovalServiceImplTest {

    private final String userId = "user";
    private final Long roleId = 1L;
    @Mock
    private UserRepository userRepository;

    @Mock
    UserRoleService userRoleService;

    @InjectMocks
    private UserApprovalServiceImpl userApprovalService;

//    @Test
//    @DisplayName("회원 승인 요청 목록 조회 테스트")
//    void waitingUserList() {
//
//        int page = 0;
//        int size = 10;
//
//        List<WaitingUser> waitingUserList = Collections.emptyList();
//
//        Pageable pageable = PageRequest.of(page, size, Sort.by("lastAccess").ascending());
//
//        when(userRepository.findWaitingUsers(pageable)).thenReturn(waitingUserList);
//
//        List<WaitingUser> result = userApprovalService.waitingUserList(pageable);
//
//        assertEquals(waitingUserList, result);
//    }


    @Test
    @DisplayName("회원 승인 테스트")
    void approve() throws Exception {

        UserRoleCreateRequest request = new UserRoleCreateRequest(userId, Collections.singletonList(roleId));

        User user = User.builder().
                userId(userId)
                .userState(UserState.NOT_APPROVED)
                .build();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        userApprovalService.approve(request);

        assertEquals(UserState.APPROVED, user.getUserState());

        verify(userRepository, times(1)).save(user);

        verify(userRoleService, times(1)).createUserRole(request);
    }

    @Test
    @DisplayName("회원 승인 요청 거절 테스트")
    void reject() {

        userApprovalService.reject(userId);

        verify(userRepository, times(1)).deleteById(userId);
    }
}
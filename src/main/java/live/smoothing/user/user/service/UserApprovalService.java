package live.smoothing.user.user.service;

import live.smoothing.user.user.dto.WaitingUser;
import live.smoothing.user.userrole.dto.request.UserRoleCreateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserApprovalService {

    Page<WaitingUser> waitingUserList(Pageable pageable);

    void approve(UserRoleCreateRequest request);

    void reject(String userId);


}

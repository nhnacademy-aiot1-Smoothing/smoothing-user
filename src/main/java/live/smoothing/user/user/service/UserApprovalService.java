package live.smoothing.user.user.service;

import live.smoothing.user.user.dto.WaitingUser;
import live.smoothing.user.userrole.dto.request.UserRoleCreateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserApprovalService {

    List<WaitingUser> waitingUserList(int page, int size);

    void approve(UserRoleCreateRequest request);

    void reject(String userId);


}

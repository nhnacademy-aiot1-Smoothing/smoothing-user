package live.smoothing.user.user.service;

import live.smoothing.user.user.dto.WaitingUser;
import live.smoothing.user.userrole.dto.request.UserRoleCreateRequest;

import java.util.List;

public interface UserApprovalService {

    List<WaitingUser> waitingUserList(int page, int size);

    List<WaitingUser> waitingUserList();

    void approve(UserRoleCreateRequest request);

    void reject(String userId);


}

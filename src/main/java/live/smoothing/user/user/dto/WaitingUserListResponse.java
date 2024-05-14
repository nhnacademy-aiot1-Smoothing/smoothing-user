package live.smoothing.user.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class WaitingUserListResponse {

        private final List<WaitingUser> waitingUsers;

        private final int totalPage;
}

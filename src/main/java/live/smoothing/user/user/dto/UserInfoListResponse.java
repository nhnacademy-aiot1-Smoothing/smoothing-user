package live.smoothing.user.user.dto;

import live.smoothing.user.user.dto.response.UserInfoResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class UserInfoListResponse {
    private List<UserInfoResponse> users;
    private int totalPage;
}

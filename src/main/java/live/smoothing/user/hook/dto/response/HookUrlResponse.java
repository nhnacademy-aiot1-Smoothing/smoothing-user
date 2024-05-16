package live.smoothing.user.hook.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HookUrlResponse {

    private String userId;
    private String hookUrl;
}

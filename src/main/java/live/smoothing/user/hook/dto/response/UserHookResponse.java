package live.smoothing.user.hook.dto.response;

import live.smoothing.user.hook.entity.HookType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserHookResponse {

    private HookType hookType;
    private String hookUrl;
}

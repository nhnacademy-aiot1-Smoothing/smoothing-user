package live.smoothing.user.hook.dto.response;

import live.smoothing.user.hook.entity.HookEventType;
import live.smoothing.user.hook.entity.HookType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WebHookResponse {

    private String hookName;
    private HookType hookType;
    private String hookUrl;
    private List<HookEventType> eventTypes;
}

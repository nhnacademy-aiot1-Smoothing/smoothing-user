package live.smoothing.user.hook.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HookModifyRequest {

    private Integer hookTypeId;
    private String hookUrl;
}

package live.smoothing.user.hook.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserHookResponse {

    private Integer hookTypeId;
    private String hookTypeName;
    private String hookUrl;
}

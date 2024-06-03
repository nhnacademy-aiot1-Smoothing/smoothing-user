package live.smoothing.user.hook.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class UserHookCreateRequest {

    private String hookName;
    private Integer hookTypeId;
    private String hookUrl;
    private List<Integer> hookEventIds;
}

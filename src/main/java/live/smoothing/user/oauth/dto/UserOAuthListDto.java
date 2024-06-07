package live.smoothing.user.oauth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class UserOAuthListDto {
    private final List<String> registeredOAuthList;
}

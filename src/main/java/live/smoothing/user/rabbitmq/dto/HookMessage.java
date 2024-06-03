package live.smoothing.user.rabbitmq.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HookMessage {

    private String userRole;
    private String message;
}

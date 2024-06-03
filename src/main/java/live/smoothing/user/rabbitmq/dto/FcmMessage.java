package live.smoothing.user.rabbitmq.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FcmMessage {

    private String title;
    private String body;
}

package live.smoothing.user.common.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class MessageResponse {

    @NotNull
    private final String message;

    public MessageResponse(String message) {
        this.message = message;
    }
}

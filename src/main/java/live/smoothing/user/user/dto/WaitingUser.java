package live.smoothing.user.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
@AllArgsConstructor
public class WaitingUser {

    private String userId;
    private String userName;
    private LocalDateTime requestDate;

}

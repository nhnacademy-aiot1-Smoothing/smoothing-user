package live.smoothing.user.point.dto;

import live.smoothing.user.point.entity.PointDetailType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PointRegisterRequest {

    private Long pointDetailId;
    private String userId;
    private Long pointDetailAmount;
    private PointDetailType pointDetailType;
}
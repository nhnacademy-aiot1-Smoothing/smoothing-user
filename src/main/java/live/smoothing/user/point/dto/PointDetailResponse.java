package live.smoothing.user.point.dto;

import live.smoothing.user.point.entity.PointDetailType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PointDetailResponse {
    private Long pointDetailId;
    private String userId;
    private Long pointDetailAmount;
    private PointDetailType pointDetailType;
    private LocalDateTime pointRecordDate;
}

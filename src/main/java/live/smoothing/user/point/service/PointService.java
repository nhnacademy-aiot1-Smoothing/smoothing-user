package live.smoothing.user.point.service;

import live.smoothing.user.point.dto.PointDetailResponse;
import live.smoothing.user.point.dto.PointRegisterRequest;

import java.util.List;

public interface PointService {

    void createPoint(PointRegisterRequest request);

    List<PointDetailResponse> getPointDetails(Long userId);

    Long getBalance();
}

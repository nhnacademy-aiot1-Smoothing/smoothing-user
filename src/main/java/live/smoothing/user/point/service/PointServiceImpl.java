package live.smoothing.user.point.service;

import live.smoothing.user.advice.ErrorCode;
import live.smoothing.user.advice.exception.ServiceException;
import live.smoothing.user.point.dto.PointDetailResponse;
import live.smoothing.user.point.dto.PointRegisterRequest;
import live.smoothing.user.point.entity.PointDetail;
import live.smoothing.user.point.entity.PointDetailType;
import live.smoothing.user.point.repository.PointRepository;
import live.smoothing.user.user.entity.User;
import live.smoothing.user.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PointServiceImpl implements PointService {

    private final PointRepository pointRepository;
    private final UserRepository userRepository;

    @Override
    public void createPoint(PointRegisterRequest request) {

        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new ServiceException(ErrorCode.USER_NOT_FOUND));

        Long accumulatedPoints = pointRepository.sumAccumulatedPointByUser(user);
        Long usedPoints = pointRepository.sumUsedPointByUser(user);

        if (request.getPointDetailType() == PointDetailType.USAGE && request.getPointDetailAmount() > accumulatedPoints - usedPoints) {
            throw new ServiceException(ErrorCode.INSUFFICIENT_BALANCE);
        }

        pointRepository.save(new PointDetail(user, request.getPointDetailAmount(), request.getPointDetailType(), LocalDateTime.now()));
    }

    @Override
    public List<PointDetailResponse> getPointDetails(String userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ServiceException(ErrorCode.USER_NOT_FOUND));

        List<PointDetail> pointDetails = pointRepository.findByUser(user);

        return pointDetails.stream()
                .map(pointDetail -> new PointDetailResponse(
                        pointDetail.getPointDetailId(),
                        userId,
                        pointDetail.getPointDetailAmount(),
                        pointDetail.getPointDetailType(),
                        pointDetail.getPointRecordDate()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public Long getBalance(String userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ServiceException(ErrorCode.USER_NOT_FOUND));

        Long accumulatedPoints = pointRepository.sumAccumulatedPointByUser(user);
        Long usedPoints = pointRepository.sumUsedPointByUser(user);

        return accumulatedPoints - usedPoints;
    }
}

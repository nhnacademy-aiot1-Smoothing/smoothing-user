package live.smoothing.user.point.controller;

import live.smoothing.user.common.dto.MessageResponse;
import live.smoothing.user.point.dto.PointDetailResponse;
import live.smoothing.user.point.dto.PointRegisterRequest;
import live.smoothing.user.point.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user/point")
public class PointController {

    private final PointService pointService;

    @Autowired
    public PointController(PointService pointService) {

        this.pointService = pointService;
    }

    @PostMapping
    public ResponseEntity<MessageResponse> createPoint(@Valid @RequestBody PointRegisterRequest request) {

        pointService.createPoint(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse("포인트 적립 완료"));
    }

    @GetMapping
    public ResponseEntity<List<PointDetailResponse>> getPointDetails(@RequestHeader("X-USER-ID") String userId) {

        return ResponseEntity.ok(pointService.getPointDetails(userId));
    }

    @GetMapping("/balance")
    public ResponseEntity<Long> getBalance(@RequestHeader("X-USER-ID") String userId) {

        return ResponseEntity.ok(pointService.getBalance(userId));
    }
}
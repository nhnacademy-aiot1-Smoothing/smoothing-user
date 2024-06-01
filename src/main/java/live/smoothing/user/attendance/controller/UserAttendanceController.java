package live.smoothing.user.attendance.controller;

import live.smoothing.user.attendance.dto.response.UserAttendanceResponse;
import live.smoothing.user.attendance.service.AttendanceService;
import live.smoothing.user.common.dto.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/attendance")
public class UserAttendanceController {

    private final AttendanceService attendanceService;

    @GetMapping("/list/{year}/{month}") // userId로 출석 일자 가져옴
    public ResponseEntity<UserAttendanceResponse> getAttendanceList(@RequestHeader("X-USER-ID") String userId,
                                                                    @PathVariable("year") int year,
                                                                    @PathVariable("month") int month) {

        return ResponseEntity.ok().body(attendanceService.getUserAttendance(userId, year, month));
    }

    @PostMapping// 출석체크
    public ResponseEntity<MessageResponse> doAttendanceCheck(@RequestHeader("X-USER-ID") String userId) {

        attendanceService.attendanceCheck(userId);

        return ResponseEntity.ok().body(new MessageResponse("출석체크 되었습니다."));
    }
}

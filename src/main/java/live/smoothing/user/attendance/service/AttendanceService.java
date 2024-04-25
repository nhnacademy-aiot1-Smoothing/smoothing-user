package live.smoothing.user.attendance.service;

import live.smoothing.user.attendance.dto.response.UserAttendanceResponse;

public interface AttendanceService {

    UserAttendanceResponse getUserAttendance(String userId);

    void attendanceCheck(String userId);
}

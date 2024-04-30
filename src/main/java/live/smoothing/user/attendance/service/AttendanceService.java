package live.smoothing.user.attendance.service;

import live.smoothing.user.attendance.dto.response.UserAttendanceResponse;

public interface AttendanceService {

    UserAttendanceResponse getUserAttendance(String userId, int year, int month);

    void attendanceCheck(String userId);
}

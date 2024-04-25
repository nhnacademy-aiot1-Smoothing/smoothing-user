package live.smoothing.user.attendance.service.impl;

import live.smoothing.user.advice.ErrorCode;
import live.smoothing.user.advice.exception.ServiceException;
import live.smoothing.user.attendance.dto.response.UserAttendanceResponse;
import live.smoothing.user.attendance.entity.Attendance;
import live.smoothing.user.attendance.repository.AttendanceRepository;
import live.smoothing.user.attendance.service.AttendanceService;
import live.smoothing.user.user.entity.User;
import live.smoothing.user.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final UserRepository userRepository;
    private final AttendanceRepository attendanceRepository;

    @Override
    public UserAttendanceResponse getUserAttendance(String userId) {

        List<Attendance> attendanceList = attendanceRepository.findByUser_UserId(userId);

        List<LocalDate> attendanceDateList = attendanceList.stream()
                .map(Attendance::getAttendanceDate)
                .collect(Collectors.toList());

        return new UserAttendanceResponse(userId, attendanceDateList);
    }

    @Override
    public void attendanceCheck(String userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ServiceException(ErrorCode.USER_NOT_FOUND));

        LocalDate today = LocalDate.now();
        boolean isChecked = attendanceRepository.existsByUser_UserIdAndAttendanceDate(userId, today);

        if (isChecked) {
            throw new ServiceException(ErrorCode.ATTENDANCE_ALREADY_CHECKED);
        }

        Attendance attendance = new Attendance(user, LocalDate.now());

        attendanceRepository.save(attendance);
    }
}

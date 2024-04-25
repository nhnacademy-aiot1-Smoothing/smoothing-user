package live.smoothing.user.attendance.repository;

import live.smoothing.user.attendance.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    List<Attendance> findByUser_UserId(String userId);

    boolean existsByUser_UserIdAndAttendanceDate(String userId, LocalDate attendanceDate);
}

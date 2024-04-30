package live.smoothing.user.attendance.dto.response;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class UserAttendanceResponse {

    private String userId;
    private List<LocalDate> attendanceDate;
}

package live.smoothing.user.point.repository;

import live.smoothing.user.point.entity.PointDetail;
import live.smoothing.user.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PointRepository extends JpaRepository<PointDetail, Long> {

    List<PointDetail> findByUser(User user);

    @Query("SELECT COALESCE(SUM(pd.pointDetailAmount), 0) FROM PointDetail pd WHERE pd.user = :user")
    Long sumAccumulatedAndUsedPointByUser(@Param("user") User user);
}

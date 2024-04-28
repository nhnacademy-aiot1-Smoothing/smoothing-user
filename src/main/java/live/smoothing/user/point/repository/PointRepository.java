package live.smoothing.user.point.repository;

import live.smoothing.user.point.entity.PointDetail;
import live.smoothing.user.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PointRepository extends JpaRepository<PointDetail, Long> {

    List<PointDetail> findByUser(User user);

    @Query("SELECT COALESCE(SUM(CASE WHEN pd.pointDetailType = 'ACCUMULATION' THEN pd.pointDetailAmount ELSE 0 END), 0) " +
            " + COALESCE(SUM(CASE WHEN pd.pointDetailType = 'EVENT_ACCUMULATION' THEN pd.pointDetailAmount ELSE 0 END), 0) " +
            "FROM PointDetail pd WHERE pd.user = :user")
    Long sumAccumulatedPointByUser(@Param("user") User user);

    @Query("SELECT COALESCE(SUM(pd.pointDetailAmount), 0) FROM PointDetail pd WHERE pd.user = :user AND pd.pointDetailType = 'USAGE'")
    Long sumUsedPointByUser(@Param("user") User user);
}

package live.smoothing.user.point.repository;

import live.smoothing.user.point.entity.PointDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<PointDetail, Long> {

}

package live.smoothing.user.point.entity;

import live.smoothing.user.user.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "point_details")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PointDetail {

    @Id
    @Column(name = "point_detail_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pointDetailId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "point_detail_amount")
    private Long pointDetailAmount;

    @Column(name = "point_detail_type")
    @Enumerated(EnumType.STRING)
    private PointDetailType pointDetailType;

    @Column(name = "point_record_date")
    private LocalDateTime pointRecordDate;

    public PointDetail(User user, Long pointDetailAmount, PointDetailType pointDetailType, LocalDateTime pointRecordDate) {

        this.user = user;
        this.pointDetailAmount = pointDetailAmount;
        this.pointDetailType = pointDetailType;
        this.pointRecordDate = pointRecordDate;
    }
}

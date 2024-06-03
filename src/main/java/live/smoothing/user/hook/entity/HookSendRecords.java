package live.smoothing.user.hook.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "hook_send_records")
public class HookSendRecords {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hook_record_id")
    private Long hookRecordId;

    @ManyToOne
    @JoinColumn(name = "hook_list_id")
    private UserHook userHookList;

    @Column(name = "hook_send_time")
    private LocalDateTime hookSendTime;

    @Column(name = "hook_send_status")
    private String hookSendStatus;
}

package live.smoothing.user.hook.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "hook_event_types")
public class HookEventType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hook_event_id")
    private Integer hookEventId;

    @Enumerated(EnumType.STRING)
    @Column(name = "hook_event_type")
    private EventType hookEventType;
}

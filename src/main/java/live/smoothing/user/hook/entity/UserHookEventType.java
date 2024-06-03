package live.smoothing.user.hook.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_hook_event_types")
public class UserHookEventType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_hook_event_type_id")
    private Integer userHookEventTypeId;

    @ManyToOne
    @JoinColumn(name = "hook_list_id")
    private UserHook userHook;

    @ManyToOne
    @JoinColumn(name = "hook_event_id")
    private HookEventType hookEventType;

    public UserHookEventType(UserHook userHook, HookEventType hookEventType) {
        this.userHook = userHook;
        this.hookEventType = hookEventType;
    }
}

package live.smoothing.user.hook.entity;

import live.smoothing.user.user.entity.User;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_hooks")
public class UserHook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hook_list_id")
    private Integer hookListId;

    @Column(name = "hook_name")
    private String hookName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "hook_url")
    private String hookUrl;

    @ManyToOne
    @JoinColumn(name = "hook_type_id")
    private HookType hookType;

    public UserHook(String hookName, HookType hookType, User user, String hookUrl) {

         this.hookName = hookName;
         this.hookType = hookType;
         this.user = user;
         this.hookUrl = hookUrl;
    }

    public void modifyHookName(String hookName) {
        this.hookName = hookName;
    }

    public void modifyHookType(HookType hookType) {
        this.hookType = hookType;
    }

    public void modifyHookUrl(String hookUrl) {
        this.hookUrl = hookUrl;
    }
}

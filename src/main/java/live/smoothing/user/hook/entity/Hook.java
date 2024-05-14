package live.smoothing.user.hook.entity;

import live.smoothing.user.user.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "hooks")
public class Hook {

    @Id
    @Column(name = "hook_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer hookId;

    @Column(name = "hook_url")
    private String hookUrl;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "hook_type_id")
    private HookType hookType;

    public Hook (String hookUrl, User user, HookType hookType) {

        this.hookUrl = hookUrl;
        this.user = user;
        this.hookType = hookType;
    }

    public void modifyHookUrl(HookType hookType, String hookUrl) {

        this.hookType = hookType;
        this.hookUrl = hookUrl;
    }
}

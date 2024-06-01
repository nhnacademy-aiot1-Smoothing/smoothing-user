package live.smoothing.user.hook.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "hook_types")
public class HookType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hook_type_id")
    private Integer hookTypeId;

    @Column(name = "hook_type_name")
    private String hookTypeName;

    public HookType(String hookTypeName) {

        this.hookTypeName = hookTypeName;
    }

    public void modifyHookTypeName(String hookTypeName) {

        this.hookTypeName = hookTypeName;
    }
}

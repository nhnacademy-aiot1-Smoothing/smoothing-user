package live.smoothing.user.organization.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Getter
@DynamicUpdate
@Table(name = "organization")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Organization {

    @Id
    @Column(name = "organization_name")
    private String organizationName;

    @Column(name = "business")
    private String business;

    @Column(name = "location")
    private String location;
}
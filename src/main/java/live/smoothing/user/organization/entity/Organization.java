package live.smoothing.user.organization.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Getter
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

    public Organization(String organizationName, String business, String location) {

        this.organizationName = organizationName;
        this.business = business;
        this.location = location;
    }

    public void modifyOrganization(String organizationName, String business, String location) {

        this.organizationName = organizationName;
        this.business = business;
        this.location = location;
    }
}
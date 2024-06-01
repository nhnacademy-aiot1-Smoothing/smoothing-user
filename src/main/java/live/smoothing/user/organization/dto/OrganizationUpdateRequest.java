package live.smoothing.user.organization.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrganizationUpdateRequest {

    private String organizationName;
    private String business;
    private String location;

    public OrganizationUpdateRequest(String organizationName, String business, String location) {

        this.organizationName = organizationName;
        this.business = business;
        this.location = location;
    }
}
package live.smoothing.user.organization.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrganizationRegisterRequest {

    private String organizationName;
    private String business;
    private String location;

    public OrganizationRegisterRequest(String organizationName, String business, String location) {

        this.organizationName = organizationName;
        this.business = business;
        this.location = location;
    }
}

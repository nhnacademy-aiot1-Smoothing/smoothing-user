package live.smoothing.user.organization.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrganizationResponse {

    private String organizationName;
    private String business;
    private String location;
}
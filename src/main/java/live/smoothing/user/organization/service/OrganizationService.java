package live.smoothing.user.organization.service;

import live.smoothing.user.organization.dto.OrganizationRegisterRequest;
import live.smoothing.user.organization.dto.OrganizationResponse;
import live.smoothing.user.organization.dto.OrganizationUpdateRequest;
import org.springframework.stereotype.Service;

@Service
public interface OrganizationService {
    void createOrganization(OrganizationRegisterRequest request);

    OrganizationResponse getOrganization();

    void updateOrganization(OrganizationUpdateRequest request);
}

package live.smoothing.user.organization.service;

import live.smoothing.user.organization.dto.OrganizationRegisterRequest;
import live.smoothing.user.organization.dto.OrganizationUpdateRequest;
import live.smoothing.user.organization.entity.Organization;
import org.springframework.stereotype.Service;

@Service
public interface OrganizationService {
    void createOrganization(OrganizationRegisterRequest request);

    Organization getOrganization();

    void updateOrganization(String organizationName, OrganizationUpdateRequest request);
}

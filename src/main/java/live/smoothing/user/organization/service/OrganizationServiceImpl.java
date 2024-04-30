package live.smoothing.user.organization.service;

import live.smoothing.user.organization.dto.OrganizationRegisterRequest;
import live.smoothing.user.organization.dto.OrganizationResponse;
import live.smoothing.user.organization.dto.OrganizationUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    @Override
    public void createOrganization(OrganizationRegisterRequest request) {

    }

    @Override
    public OrganizationResponse getOrganization() {

        return null;
    }

    @Override
    public void updateOrganization(OrganizationUpdateRequest request) {

    }
}

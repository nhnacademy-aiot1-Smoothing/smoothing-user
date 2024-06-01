package live.smoothing.user.organization.service;

import live.smoothing.user.advice.ErrorCode;
import live.smoothing.user.advice.exception.ServiceException;
import live.smoothing.user.organization.dto.OrganizationRegisterRequest;
import live.smoothing.user.organization.dto.OrganizationUpdateRequest;
import live.smoothing.user.organization.entity.Organization;
import live.smoothing.user.organization.repository.OrganizationRepository;
import live.smoothing.user.role.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;

    @Override
    public void createOrganization(OrganizationRegisterRequest request) {

        long organizationCount = organizationRepository.count();

        if(organizationCount > 0) {
            throw new ServiceException(ErrorCode.MULTIPLE_ORGANIZATION_NOT_ALLOWED);
        }
        organizationRepository.save(new Organization(request.getOrganizationName(), request.getBusiness(), request.getLocation()));
    }

    @Override
    public Organization getOrganization() {

        return organizationRepository.findAll().get(0);
    }

    @Override
    @Transactional
    public void updateOrganization(String organizationName, OrganizationUpdateRequest request) {

        Organization organization = organizationRepository.findByOrganizationName(organizationName);
        if (organization == null) {
            throw new ServiceException(ErrorCode.ORGANIZATION_NOT_FOUND);
        }

        Organization newOrganization = new Organization(
                request.getOrganizationName(),
                request.getBusiness(),
                request.getLocation()
        );

        organizationRepository.delete(organization);
        organizationRepository.save(newOrganization);
    }
}

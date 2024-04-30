package live.smoothing.user.organization.repository;

import live.smoothing.user.organization.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    Organization findByOrganizationName(String organizationName);
}

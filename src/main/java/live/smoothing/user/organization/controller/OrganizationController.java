package live.smoothing.user.organization.controller;

import live.smoothing.user.common.dto.MessageResponse;
import live.smoothing.user.organization.dto.OrganizationRegisterRequest;
import live.smoothing.user.organization.dto.OrganizationUpdateRequest;
import live.smoothing.user.organization.entity.Organization;
import live.smoothing.user.organization.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/organization")
public class OrganizationController {

    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {

        this.organizationService = organizationService;
    }

    @GetMapping
    public ResponseEntity<Organization> getOrganization() {

        Organization organization = organizationService.getOrganization();
        return ResponseEntity.ok(organization);
    }

    @PostMapping
    public ResponseEntity<MessageResponse> createOrganization(@Valid @RequestBody OrganizationRegisterRequest request) {

        organizationService.createOrganization(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse("조직 생성 완료"));
    }

    @PutMapping("/{organizationName}")
    public ResponseEntity<MessageResponse> updateOrganization(@Valid @RequestBody OrganizationUpdateRequest request, @PathVariable String organizationName) {

        organizationService.updateOrganization(organizationName, request);
        return ResponseEntity.ok(new MessageResponse("조직 수정 완료"));
    }
}

package live.smoothing.user.organization.controller;

import live.smoothing.user.common.dto.MessageResponse;
import live.smoothing.user.organization.dto.OrganizationRegisterRequest;
import live.smoothing.user.organization.dto.OrganizationResponse;
import live.smoothing.user.organization.dto.OrganizationUpdateRequest;
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
    public ResponseEntity<OrganizationResponse> getOrganization() {

        OrganizationResponse organizationResponse = organizationService.getOrganization();
        return ResponseEntity.ok(organizationResponse);
    }

    @PostMapping
    public ResponseEntity<MessageResponse> createOrganization(@Valid @RequestBody OrganizationRegisterRequest request) {

        organizationService.createOrganization(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse("조직 생성 완료"));
    }

    @PutMapping
    public ResponseEntity<MessageResponse> updateOrganization(@Valid @RequestBody OrganizationUpdateRequest request) {

        organizationService.updateOrganization(request);
        return ResponseEntity.ok(new MessageResponse("조직 수정 완료"));
    }
}

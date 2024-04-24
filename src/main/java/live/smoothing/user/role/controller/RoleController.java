package live.smoothing.user.role.controller;

import live.smoothing.user.role.dto.response.RoleResponse;
import live.smoothing.user.role.dto.request.CreateRoleRequest;
import live.smoothing.user.role.dto.request.UpdateRoleRequest;
import live.smoothing.user.role.service.RoleService;
import live.smoothing.user.common.dto.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user/role")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {

        this.roleService = roleService;
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<RoleResponse> getRole(@PathVariable Long roleId) {

        RoleResponse roleResponse = roleService.getRole(roleId);
        return ResponseEntity.ok(roleResponse);
    }

    @GetMapping("/list")
    public ResponseEntity<List<RoleResponse>> getAllRoles() {

        List<RoleResponse> roleResponse = roleService.getAllRoles();
        return ResponseEntity.ok(roleResponse);
    }

    @PostMapping
    public ResponseEntity<MessageResponse> createRole(@Valid @RequestBody CreateRoleRequest request) {

        roleService.createRole(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse("권한 정보 생성 완료"));
    }

    @PutMapping("/{roleId}")
    public ResponseEntity<MessageResponse> updateRole(@PathVariable Long roleId,
                                                      @Valid @RequestBody UpdateRoleRequest request) {

        roleService.updateRole(roleId, request);
        return ResponseEntity.ok(new MessageResponse("권한명 수정 완료"));
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<MessageResponse> deleteRole(@PathVariable Long roleId) {

        roleService.deleteRole(roleId);
        return ResponseEntity.ok(new MessageResponse("권한 정보 삭제 완료"));
    }
}

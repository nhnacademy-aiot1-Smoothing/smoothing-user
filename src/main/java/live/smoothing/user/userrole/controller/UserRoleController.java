package live.smoothing.user.userrole.controller;

import live.smoothing.user.common.dto.MessageResponse;
import live.smoothing.user.userrole.dto.request.UserRoleCreateRequest;
import live.smoothing.user.userrole.dto.request.UserRoleModifyRequest;
import live.smoothing.user.userrole.dto.response.UserRoleResponse;
import live.smoothing.user.userrole.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/userRole")
public class UserRoleController {

    private final UserRoleService userRoleService;

    @PostMapping
    public ResponseEntity<MessageResponse> createUserRole(@RequestBody UserRoleCreateRequest request) {

        return ResponseEntity.ok().body(userRoleService.createUserRole(request));
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserRoleResponse>> getUserRolesByUserId(@RequestParam(name = "userId") String userId) {

        return ResponseEntity.ok().body(userRoleService.getUserRolesByUserId(userId));
    }

    @PutMapping
    public ResponseEntity<MessageResponse> modifyUserRole(@RequestBody UserRoleModifyRequest request) {

        return ResponseEntity.ok().body(userRoleService.modifyUserRole(request));
    }

    @DeleteMapping("/{userRoleId}")
    public ResponseEntity<MessageResponse> deleteUserRole(@PathVariable("userRoleId") Long userRoleId) {

        return ResponseEntity.ok().body(userRoleService.deleteUserRole(userRoleId));
    }
}

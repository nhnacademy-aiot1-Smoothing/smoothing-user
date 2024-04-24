package live.smoothing.user.userrole.controller;

import live.smoothing.user.common.dto.MessageResponse;
import live.smoothing.user.userrole.dto.request.UserRoleCreateRequest;
import live.smoothing.user.userrole.dto.request.UserRoleModifyRequest;
import live.smoothing.user.userrole.dto.response.UserRoleResponse;
import live.smoothing.user.userrole.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/userRole")
public class UserRoleController {

    private final UserRoleService userRoleService;

    @PostMapping
    public ResponseEntity<MessageResponse> createUserRole(@RequestBody UserRoleCreateRequest request) {

        userRoleService.createUserRole(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse("회원 권한 설정 완료"));
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserRoleResponse>> getUserRolesByUserId(@RequestParam(name = "userId") String userId) {

        return ResponseEntity.ok().body(userRoleService.getUserRolesByUserId(userId));
    }

    @PutMapping
    public ResponseEntity<MessageResponse> modifyUserRole(@RequestBody UserRoleModifyRequest request) {

        userRoleService.modifyUserRole(request);
        return ResponseEntity.ok().body(new MessageResponse("회원 권한 수정 완료"));
    }

    @DeleteMapping("/{userRoleId}")
    public ResponseEntity<MessageResponse> deleteUserRole(@PathVariable("userRoleId") Long userRoleId) {

        userRoleService.deleteUserRole(userRoleId);
        return ResponseEntity.ok().body(new MessageResponse("회원 권한 삭제 완료"));
    }
}

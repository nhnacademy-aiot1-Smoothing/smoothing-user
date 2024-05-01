package live.smoothing.user.user.controller;

import live.smoothing.user.common.dto.MessageResponse;
import live.smoothing.user.user.dto.WaitingUser;
import live.smoothing.user.user.service.UserApprovalService;
import live.smoothing.user.userrole.dto.request.UserRoleCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserApprovalController {

    private final UserApprovalService userApprovalService;

    @GetMapping("/paging/waitingUserList")
    public ResponseEntity<List<WaitingUser>> getWaitingUserList(@RequestParam("page")int page,
                                                                @RequestParam("size") int size) {

        return ResponseEntity.ok().body(userApprovalService.waitingUserList(page, size));
    }

    @GetMapping("/waitingUserList")
    public ResponseEntity<List<WaitingUser>> getWaitingUserList() {

        return ResponseEntity.ok().body(userApprovalService.waitingUserList());
    }

    @PutMapping("/approve")
    public ResponseEntity<MessageResponse> approveUser(@RequestBody UserRoleCreateRequest request) {

        userApprovalService.approve(request);

        return ResponseEntity.ok(new MessageResponse("회원 승인 완료"));
    }

    @DeleteMapping("/reject/{userId}")
    public ResponseEntity<MessageResponse> rejectUser(@PathVariable String userId) {

        userApprovalService.reject(userId);

        return ResponseEntity.ok(new MessageResponse("회원 승인 요청 거절"));
    }

}

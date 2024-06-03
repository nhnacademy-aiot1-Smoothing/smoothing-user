package live.smoothing.user.hook.controller;

import live.smoothing.user.common.dto.MessageResponse;
import live.smoothing.user.hook.dto.request.UserHookCreateRequest;
import live.smoothing.user.hook.dto.request.UserHookUpdateRequest;
import live.smoothing.user.hook.dto.response.WebHookResponse;
import live.smoothing.user.hook.entity.UserHook;
import live.smoothing.user.hook.service.UserHookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user/webhook")

public class UserHookController {

    private final UserHookService userHookService;

    @GetMapping
    public ResponseEntity<List<WebHookResponse>> getUserHooks(@RequestHeader("X-USER-ID") String userId) {

        return ResponseEntity.ok().body(userHookService.getUserHookList(userId));
    }

    @GetMapping("/{hookListId}")
    public ResponseEntity<WebHookResponse> getUserHook(@RequestHeader("X-USER-ID") String userId, @PathVariable Integer hookListId) {

        return ResponseEntity.ok().body(userHookService.getUserHook(userId, hookListId));
    }

    @PostMapping
    public ResponseEntity<MessageResponse> createUserHook(@RequestHeader("X-USER-ID") String userId, @RequestBody UserHookCreateRequest request) {

        userHookService.createUserHook(userId, request);

        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse("웹훅 생성 완료"));
    }

    @PutMapping("/{hookListId}")
    public ResponseEntity<MessageResponse> updateUserHook(@RequestHeader("X-USER-ID") String userId,
                                                          @PathVariable Integer hookListId,
                                                          @RequestBody UserHookUpdateRequest request) {

        userHookService.updateUserHook(userId, hookListId, request);

        return ResponseEntity.ok().body(new MessageResponse("웹훅 수정 완료"));
    }

    @DeleteMapping("/{hookListId}")
    public ResponseEntity<MessageResponse> deleteUserHook(@RequestHeader("X-USER-ID") String userId,
                                                          @PathVariable Integer hookListId) {

        userHookService.deleteUserHook(userId, hookListId);

        return ResponseEntity.ok().body(new MessageResponse("웹훅 삭제 완료"));
    }

}

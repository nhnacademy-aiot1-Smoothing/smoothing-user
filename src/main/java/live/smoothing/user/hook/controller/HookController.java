package live.smoothing.user.hook.controller;

import live.smoothing.user.common.dto.MessageResponse;
import live.smoothing.user.hook.dto.request.HookCreateRequest;
import live.smoothing.user.hook.dto.request.HookModifyRequest;
import live.smoothing.user.hook.dto.response.UserHookResponse;
import live.smoothing.user.hook.entity.Hook;
import live.smoothing.user.hook.service.HookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user/hook")
public class HookController {

    private final HookService hookService;

    @GetMapping
    public ResponseEntity<UserHookResponse> getUserHook(@RequestHeader("X-USER-ID") String userId) {

        return ResponseEntity.ok(hookService.getHook(userId));
    }

    @PostMapping
    public ResponseEntity<MessageResponse> createUserHook(@RequestHeader("X-USER-ID") String userId,
                                                          @RequestBody HookCreateRequest request) {

        hookService.createHook(userId, request);

        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse("훅 생성 완료"));
    }

    @PutMapping
    public ResponseEntity<MessageResponse> modifyUserHook(@RequestHeader("X-USER-ID") String userId,
                                                          @RequestBody HookModifyRequest request) {

        hookService.modifyHook(userId, request);

        return ResponseEntity.ok().body(new MessageResponse("훅 수정 완료"));
    }

    @DeleteMapping
    public ResponseEntity<MessageResponse> deleteUserHook(@RequestHeader("X-USER-ID") String userId) {

        hookService.deleteHook(userId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}

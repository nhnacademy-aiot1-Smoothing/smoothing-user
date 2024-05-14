package live.smoothing.user.hook.controller;

import live.smoothing.user.hook.entity.Hook;
import live.smoothing.user.hook.service.HookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user/hook")
public class HookController {

    private final HookService hookService;

    @GetMapping
    public ResponseEntity<Hook> getUserHook(@RequestHeader("X-USER-ID") String userId) {

        return ResponseEntity.ok(hookService.getHook(userId));
    }



}

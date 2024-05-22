package live.smoothing.user.hook.controller;

import live.smoothing.user.common.dto.MessageResponse;
import live.smoothing.user.hook.dto.request.HookTypeCreateRequest;
import live.smoothing.user.hook.dto.request.HookTypeModifyRequest;
import live.smoothing.user.hook.entity.HookType;
import live.smoothing.user.hook.service.HookTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/hookType")
public class HookTypeController {

    private final HookTypeService hookTypeService;

    @GetMapping("/{hookTypeId}")
    ResponseEntity<HookType> getHookType(@PathVariable Integer hookTypeId) {

        return ResponseEntity.ok().body(hookTypeService.getHookType(hookTypeId));
    }

    @GetMapping("/list")
    ResponseEntity<List<HookType>> getHookTypes() {

        return ResponseEntity.ok().body(hookTypeService.getHookTypes());
    }

    @PostMapping
    ResponseEntity<MessageResponse> createHookType(@RequestBody HookTypeCreateRequest request) {

        hookTypeService.createHookType(request);

        return ResponseEntity.ok().body(new MessageResponse("훅 타입 생성 완료"));
    }

    @PutMapping("/{hookTypeId}")
    ResponseEntity<MessageResponse> modifyHookType(@PathVariable Integer hookTypeId, @RequestBody HookTypeModifyRequest request) {

        hookTypeService.modifyHookType(hookTypeId, request);

        return ResponseEntity.ok().body(new MessageResponse("훅 타입 수정 완료"));
    }

    @DeleteMapping("/{hookTypeId}")
    ResponseEntity<MessageResponse> deleteHookType(@PathVariable Integer hookTypeId) {

        hookTypeService.deleteHookType(hookTypeId);

        return ResponseEntity.ok().body(new MessageResponse("훅 타입 삭제 완료"));
    }
}

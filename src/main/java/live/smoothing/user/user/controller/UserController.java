package live.smoothing.user.user.controller;

import live.smoothing.user.user.dto.request.UserCreateRequest;
import live.smoothing.user.user.dto.request.UserInfoModifyRequest;
import live.smoothing.user.user.dto.request.UserPWModifyRequest;
import live.smoothing.user.user.dto.response.UserDetailResponse;
import live.smoothing.user.user.dto.response.UserResponseTemplate;
import live.smoothing.user.user.dto.response.UserSimpleResponse;
import live.smoothing.user.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserCreateRequest request) {

        userService.createUser(request);
        return ResponseEntity.ok("사용자 회원 가입 완료");
    }

    @GetMapping("/login")
    public ResponseEntity<UserResponseTemplate<UserSimpleResponse>> getUserSimpleInfo(@RequestHeader("X-USER-ID") String userId) {

        return ResponseEntity.ok(userService.getUserSimpleInfo(userId));
    }

    @GetMapping("/profile")
    public ResponseEntity<UserResponseTemplate<UserDetailResponse>> getUserDetailInfo(@RequestHeader("X-USER-ID") String userId) {

        return ResponseEntity.ok(userService.getUserDetailInfo(userId));
    }

    @PatchMapping("/profile")
    public ResponseEntity<String> userInfoModify(@RequestHeader("X-USER-ID") String userId,
                                                 @Valid @RequestBody UserInfoModifyRequest request) {
        userService.modifyUserInfo(userId, request);
        return ResponseEntity.ok("사용자 정보 변경 완료");
    }

    @PatchMapping("/profile/password")
    public ResponseEntity<String> userPasswordModify(@RequestHeader("X-USER-ID") String userId,
                                                     @Valid @RequestBody UserPWModifyRequest request) {

        userService.modifyUserPassword(userId, request);
        return ResponseEntity.ok("사용자 비밀번호 변경 완료");
    }

    @DeleteMapping("/inactive")
    public ResponseEntity<String> userInactive(@RequestHeader("X-USER-ID") String userId){

        userService.deleteUser(userId);
        return ResponseEntity.ok("사용자 비활성화 완료");
    }
}

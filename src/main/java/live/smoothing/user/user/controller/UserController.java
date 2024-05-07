package live.smoothing.user.user.controller;

import live.smoothing.user.user.dto.request.UserCreateRequest;
import live.smoothing.user.user.dto.request.UserInfoModifyRequest;
import live.smoothing.user.user.dto.request.UserPWModifyRequest;
import live.smoothing.user.common.dto.MessageResponse;
import live.smoothing.user.user.dto.request.UserPasswordRequest;
import live.smoothing.user.user.dto.response.*;
import live.smoothing.user.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> createUser(@Valid @RequestBody UserCreateRequest request) {

        userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse("유저 회원 가입 완료"));
    }

    @GetMapping("/login")
    public ResponseEntity<UserResponseTemplate<UserSimpleResponse>> getUserSimpleInfo(@RequestHeader("X-USER-ID") String userId) {

        return ResponseEntity.ok(userService.getUserSimpleInfo(userId));
    }

    @GetMapping("/profile")
    public ResponseEntity<UserResponseTemplate<UserDetailResponse>> getUserDetailInfo(@RequestHeader("X-USER-ID") String userId) {

        return ResponseEntity.ok(userService.getUserDetailInfo(userId));
    }

    @PutMapping("/profile")
    public ResponseEntity<MessageResponse> userInfoModify(@RequestHeader("X-USER-ID") String userId,
                                                          @Valid @RequestBody UserInfoModifyRequest request) {

        userService.modifyUserInfo(userId, request);
        return ResponseEntity.ok(new MessageResponse("유저 정보 변경 완료"));
    }

    @PutMapping("/profile/password")
    public ResponseEntity<MessageResponse> userPasswordModify(@RequestHeader("X-USER-ID") String userId,
                                                              @Valid @RequestBody UserPWModifyRequest request) {

        userService.modifyUserPassword(userId, request);
        return ResponseEntity.ok(new MessageResponse("유저 비밀번호 변경 완료"));
    }

    @DeleteMapping("/inactive")
    public ResponseEntity<MessageResponse> userInactive(@RequestHeader("X-USER-ID") String userId) {

        userService.deleteUser(userId);
        return ResponseEntity.ok(new MessageResponse("유저 비활성화 완료"));
    }

    @PostMapping("/password")
    public ResponseEntity<MessageResponse> verifyUserPassword(@RequestHeader("X-USER-ID") String userId, @RequestBody UserPasswordRequest request) {

        if(userService.isCorrectUserPassword(userId, request)) {
            return ResponseEntity.ok(new MessageResponse("비밀번호 확인 완료"));
        }

        return ResponseEntity.badRequest().body(new MessageResponse("비말번호 불일치"));
    }

    @GetMapping("/profile/name")
    public ResponseEntity<String> getUserName(@RequestHeader("X-USER-ID") String userId) {

        return ResponseEntity.ok(userService.getUserName(userId));
    }

    @GetMapping("/profile/modify")
    public ResponseEntity<UserProfileResponse> getUserProfile(@RequestHeader("X-USER-ID") String userId) {

        return ResponseEntity.ok(userService.getUserProfile(userId));
    }
}

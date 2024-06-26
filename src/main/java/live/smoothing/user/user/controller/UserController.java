package live.smoothing.user.user.controller;

import live.smoothing.user.common.dto.MessageResponse;
import live.smoothing.user.user.dto.UserInfoListResponse;
import live.smoothing.user.user.dto.request.*;
import live.smoothing.user.user.dto.response.*;
import live.smoothing.user.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<MessageResponse> deleteUser(@PathVariable("userId") String userId) {

        userService.deleteUser(userId);
        return ResponseEntity.ok(new MessageResponse("유저 비활성화 완료"));
    }

    @PostMapping("/password")
    public ResponseEntity<MessageResponse> verifyUserPassword(@RequestHeader("X-USER-ID") String userId, @RequestBody UserPasswordRequest request) {

        if(userService.isCorrectUserPassword(userId, request)) {
            return ResponseEntity.ok(new MessageResponse("비밀번호 확인 완료"));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("비말번호 불일치"));
    }

    @GetMapping("/profile/name")
    public ResponseEntity<String> getUserName(@RequestHeader("X-USER-ID") String userId) {

        return ResponseEntity.ok(userService.getUserName(userId));
    }

    @GetMapping("/profile/modify")
    public ResponseEntity<UserProfileResponse> getUserProfile(@RequestHeader("X-USER-ID") String userId) {

        return ResponseEntity.ok(userService.getUserProfile(userId));
    }

    @GetMapping("/userList")
    public ResponseEntity<UserInfoListResponse> getUserList(Pageable pageable) {

        return ResponseEntity.ok().body(userService.findAllUsers(pageable));

    }

    @GetMapping("/existUser")
    public ResponseEntity<MessageResponse> existUser(@RequestParam("userId") String userId) {

        if (userService.isExistUser(userId)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse("사용 불가능한 아이디입니다."));
        }

        return ResponseEntity.ok().body(new MessageResponse("사용 가능한 아이디입니다."));
    }

    @GetMapping("/userState")
    public ResponseEntity<UserStateResponse> userState(@RequestParam("userId") String userId) {

        return ResponseEntity.ok(userService.getUserState(userId));
    }


    @PutMapping("/modify/userName")
    public ResponseEntity<MessageResponse> modifyUserName(@RequestHeader("X-USER-ID") String userId, @RequestBody UserNameModifyRequest request) {

        userService.modifyUserName(userId, request);

        return ResponseEntity.ok().body(new MessageResponse("회원 이름 변경 완료"));
    }

    @PutMapping("/modify/userEmail")
    public ResponseEntity<MessageResponse> modifyUserEmail(@RequestHeader("X-USER-ID") String userId, @RequestBody UserEmailModifyRequest request) {

        userService.modifyUserEmail(userId, request);

        return ResponseEntity.ok().body(new MessageResponse("회원 이메일 변경 완료"));
    }
}

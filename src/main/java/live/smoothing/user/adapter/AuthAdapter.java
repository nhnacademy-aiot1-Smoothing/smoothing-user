package live.smoothing.user.adapter;

import live.smoothing.user.user.dto.request.PasswordEncodingRequest;
import live.smoothing.user.user.dto.response.PasswordEncodingResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Optional;

@FeignClient(value = "auth-service", path = "/api/auth/encode")
public interface AuthAdapter {

    @PostMapping
    Optional<PasswordEncodingResponse> encodingPassword(@Valid @RequestBody PasswordEncodingRequest request);
}

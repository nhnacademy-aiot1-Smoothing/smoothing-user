package live.smoothing.user.adapter;

import live.smoothing.user.user.dto.response.PasswordDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Optional;

@FeignClient(value = "auth-service", path = "/api/auth/encode")
public interface AuthAdapter {

    @PostMapping
    Optional<PasswordDto> encodingPassword(@Valid @RequestBody PasswordDto passwordDto);
}

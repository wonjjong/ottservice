package wonjjong.dev.ottservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@RequiredArgsConstructor
public class UserSaveForm {

    @NotBlank
    @Email
    @Schema(description = "이메일", example="wonjjong.dev@gmail.com", required = true)
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    private String password;
}

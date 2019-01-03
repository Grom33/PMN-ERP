package ru.gromov.auth.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class LoginInfo {
    @NotEmpty
    @NotNull
    @NotBlank
    @Email
    private String username;

    @NotBlank
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 15, message = "Password must be min=3 and max=15")
    private String password;
}

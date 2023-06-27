package com.dev.craniumproperty.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
public class LoginDTO {
    @NotEmpty
    @Size(max = 255, message = "Email has maximum characters")
    private String email;
    @NotEmpty
    @Size(max = 255, message = "Password has maximum characters")
    private String password;
}

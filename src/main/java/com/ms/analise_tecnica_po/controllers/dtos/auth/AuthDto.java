package com.ms.analise_tecnica_po.controllers.dtos.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthDto(@NotBlank @Email String email,
    @NotBlank String password) {
}

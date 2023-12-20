package com.ms.analise_tecnica_po.controllers.dtos.users;

import com.ms.analise_tecnica_po.models.user.UserRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterUserRecordDto(
                @NotBlank String name,
                @NotNull UserRole role,

                @NotBlank @Email String email,
                @NotBlank String password) {
}

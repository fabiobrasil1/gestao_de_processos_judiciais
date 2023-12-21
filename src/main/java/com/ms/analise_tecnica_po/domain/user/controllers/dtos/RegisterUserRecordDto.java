package com.ms.analise_tecnica_po.domain.user.controllers.dtos;

import com.ms.analise_tecnica_po.domain.user.models.UserRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterUserRecordDto(
                @NotBlank String name,
                @NotNull UserRole role,

                @NotBlank @Email String email,
                @NotBlank String password) {
}

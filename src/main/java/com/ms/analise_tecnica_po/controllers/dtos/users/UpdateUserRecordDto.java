package com.ms.analise_tecnica_po.controllers.dtos.users;

import java.util.UUID;

import com.ms.analise_tecnica_po.models.user.UserRole;

import io.micrometer.common.lang.NonNull;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateUserRecordDto(
        @NotNull UUID id,

        @NotBlank String name,
        @NotNull UserRole role,

        @NotBlank String email) {

}

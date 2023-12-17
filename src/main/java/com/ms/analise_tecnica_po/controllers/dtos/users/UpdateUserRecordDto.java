package com.ms.analise_tecnica_po.controllers.dtos.users;

import java.util.UUID;

import io.micrometer.common.lang.NonNull;

import jakarta.validation.constraints.NotBlank;

public record UpdateUserRecordDto(
    @NonNull UUID id,

    @NotBlank String name,

    @NotBlank String email) {

}

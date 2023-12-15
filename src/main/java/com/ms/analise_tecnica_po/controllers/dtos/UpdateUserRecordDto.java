package com.ms.analise_tecnica_po.controllers.dtos;

import java.util.UUID;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.ToString;

public record UpdateUserRecordDto(
  @NonNull
  UUID id,
  
  @NotBlank
  String name,

  @NotBlank
  String email
) {
  
}

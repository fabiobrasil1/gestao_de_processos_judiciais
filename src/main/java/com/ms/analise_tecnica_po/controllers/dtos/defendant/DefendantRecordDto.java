package com.ms.analise_tecnica_po.controllers.dtos.defendant;

import jakarta.validation.constraints.NotBlank;

public record DefendantRecordDto(
  @NotBlank(message = "O nome do réu é obrigatório.")
  String name
) {
}

package com.ms.analise_tecnica_po.domain.defendant.controllers.dtos;

import jakarta.validation.constraints.NotBlank;

public record DefendantRecordDto(
                @NotBlank(message = "O nome do réu é obrigatório.") String name) {
}

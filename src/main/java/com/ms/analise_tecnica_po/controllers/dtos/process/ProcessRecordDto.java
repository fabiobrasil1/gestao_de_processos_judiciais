package com.ms.analise_tecnica_po.controllers.dtos.process;

import java.util.UUID;

import com.ms.analise_tecnica_po.models.ProcessModel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProcessRecordDto(
    @NotNull UUID userId,

    @NotBlank(message = "A descrição do processo é obrigatória.") String description,

    @NotBlank(message = "O número do processo é obrigatório.") String processNumber) {
  public static ProcessRecordDto fromProcessModel(ProcessModel processModel) {
    return new ProcessRecordDto(processModel.getId(), processModel.getDescription(), processModel.getProcessNumber());
  }
}
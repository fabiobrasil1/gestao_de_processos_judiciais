package com.ms.analise_tecnica_po.domain.process.controllers.dtos;

import java.util.UUID;

import com.ms.analise_tecnica_po.domain.process.models.ProcessModel;

public record ProcessDetailsRecordDto(
    UUID id,
    String description,
    String processNumber) {

  public static ProcessDetailsRecordDto fromProcessModel(ProcessModel processModel) {
    return new ProcessDetailsRecordDto(processModel.getId(), processModel.getDescription(),
        processModel.getProcessNumber());
  }
}
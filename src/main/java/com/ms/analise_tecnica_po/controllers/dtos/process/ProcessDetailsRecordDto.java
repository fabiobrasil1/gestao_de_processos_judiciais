package com.ms.analise_tecnica_po.controllers.dtos.process;

import java.util.UUID;

import com.ms.analise_tecnica_po.models.ProcessModel;

public record ProcessDetailsRecordDto(
  UUID id,  
  String description,
  String processNumber      
) {
    public static ProcessDetailsRecordDto fromProcessModel(ProcessModel processModel) {
      return new ProcessDetailsRecordDto(processModel.getId(), processModel.getDescription(), processModel.getProcessNumber());
    }
}
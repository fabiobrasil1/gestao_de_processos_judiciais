package com.ms.analise_tecnica_po.useCases.process;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ms.analise_tecnica_po.controllers.dtos.process.ProcessDetailsRecordDto;
import com.ms.analise_tecnica_po.repositoies.ProcessRepository;

@Service
public class FindProcessByIdUseCase {
  @Autowired
  private ProcessRepository repository;

  public ResponseEntity<ProcessDetailsRecordDto> execute(UUID id) {
    try {
      var process = repository.getReferenceById(id);
      return ResponseEntity.ok(ProcessDetailsRecordDto.fromProcessModel(process));
    } catch (Exception e) {
      throw e;
    }
  }
}
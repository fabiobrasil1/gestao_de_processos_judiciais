package com.ms.analise_tecnica_po.domain.process.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ms.analise_tecnica_po.domain.process.controllers.dtos.ProcessDetailsRecordDto;
import com.ms.analise_tecnica_po.domain.process.repositories.ProcessRepository;

@Service
public class FindByProcessNumberUseCase {
  @Autowired
  private ProcessRepository repository;

  public ResponseEntity<ProcessDetailsRecordDto> execute(String processNumber) {
    try {
      var process = repository.findByProcessNumber(processNumber);
      return ResponseEntity.ok(ProcessDetailsRecordDto.fromProcessModel(process));
    } catch (Exception e) {
      throw e;
    }
  }
}

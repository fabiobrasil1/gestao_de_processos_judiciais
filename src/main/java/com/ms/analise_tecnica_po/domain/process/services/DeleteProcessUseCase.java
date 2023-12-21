package com.ms.analise_tecnica_po.domain.process.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ms.analise_tecnica_po.domain.process.models.ProcessModel;
import com.ms.analise_tecnica_po.domain.process.repositories.ProcessRepository;

import jakarta.persistence.EntityNotFoundException;

import java.util.UUID;

@Service
public class DeleteProcessUseCase {

  @Autowired
  private ProcessRepository processRepository;

  public ResponseEntity<String> execute(UUID userId, String processNumber) {
    ProcessModel processToDelete = findProcessByNumberAndUser(userId, processNumber);
    deleteProcess(processToDelete.getId());
    return ResponseEntity.noContent().build();
  }

  public ProcessModel findProcessByNumberAndUser(UUID userId, String processNumber) {
    return processRepository.findByUser_IdAndProcessNumberIgnoreCase(userId, processNumber)
        .orElseThrow(() -> new EntityNotFoundException("Processo não encontrado"));
  }

  public void deleteProcess(UUID processId) {
    processRepository.deleteById(processId);
  }
}

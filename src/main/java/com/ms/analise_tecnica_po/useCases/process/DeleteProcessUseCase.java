package com.ms.analise_tecnica_po.useCases.process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ms.analise_tecnica_po.models.ProcessModel;
import com.ms.analise_tecnica_po.repositoies.ProcessRepository;

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
    // Lógica para deletar o processo
    processRepository.deleteById(processId);
  }
}

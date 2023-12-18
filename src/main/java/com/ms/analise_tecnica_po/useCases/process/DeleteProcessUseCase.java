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

  public void execute(UUID userId, String processNumber) {
    // Lógica para buscar o processo pelo número do processo e ID do usuário
    ProcessModel processToDelete = findProcessByNumberAndUser(userId, processNumber);

    // Lógica para deletar o processo
    deleteProcess(processToDelete.getId());
  }

  public ProcessModel findProcessByNumberAndUser(UUID userId, String processNumber) {
    // Lógica para buscar o processo pelo número do processo e ID do usuário
    // Certifique-se de implementar essa lógica no seu projeto
    return processRepository.findByUser_IdAndProcessNumberIgnoreCase(userId, processNumber)
        .orElseThrow(() -> new EntityNotFoundException("Processo não encontrado"));
  }

  public void deleteProcess(UUID processId) {
    // Lógica para deletar o processo
    processRepository.deleteById(processId);
  }
}

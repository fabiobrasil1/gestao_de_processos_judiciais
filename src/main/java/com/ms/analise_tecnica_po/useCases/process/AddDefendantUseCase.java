package com.ms.analise_tecnica_po.useCases.process;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ms.analise_tecnica_po.models.defendant.DefendantModel;
import com.ms.analise_tecnica_po.models.process.ProcessModel;
import com.ms.analise_tecnica_po.repositoies.DefendantRepository;
import com.ms.analise_tecnica_po.repositoies.ProcessRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AddDefendantUseCase {

  @Autowired
  private ProcessRepository processRepository;

  @Autowired
  private DefendantRepository defendantRepository;

  public void execute(UUID processId, String defendantName) {

    ProcessModel process = processRepository.findById(processId)
        .orElseThrow(() -> new EntityNotFoundException("Processo não encontrado"));

    DefendantModel newDefendant = new DefendantModel();
    newDefendant.setName(defendantName);

    process.addDefendant(newDefendant);

    defendantRepository.save(newDefendant);
    processRepository.save(process);
  }

  public ProcessModel findProcessById(UUID processId) {
    return processRepository.findById(processId)
        .orElseThrow(() -> new EntityNotFoundException("Processo não encontrado"));
  }
}
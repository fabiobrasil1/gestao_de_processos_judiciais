package com.ms.analise_tecnica_po.domain.process.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.analise_tecnica_po.domain.defendant.models.DefendantModel;
import com.ms.analise_tecnica_po.domain.defendant.repositories.DefendantRepository;
import com.ms.analise_tecnica_po.domain.process.models.ProcessModel;
import com.ms.analise_tecnica_po.domain.process.repositories.ProcessRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AddDefendantUseCase {

  @Autowired
  private ProcessRepository processRepository;

  @Autowired
  private DefendantRepository defendantRepository;

  public void execute(String processNumber, String defendantName) {

    ProcessModel process = findbyProcessNumber(processNumber);

    DefendantModel newDefendant = new DefendantModel();
    newDefendant.setName(defendantName);

    process.addDefendant(newDefendant);

    defendantRepository.save(newDefendant);
    processRepository.save(process);
  }

  public ProcessModel findbyProcessNumber(String processNumber) {
    return processRepository.findByProcessNumber(processNumber);
  }
}
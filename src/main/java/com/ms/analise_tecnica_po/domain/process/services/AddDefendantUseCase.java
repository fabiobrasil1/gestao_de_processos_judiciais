package com.ms.analise_tecnica_po.domain.process.services;

import java.util.UUID;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.analise_tecnica_po.domain.defendant.models.DefendantModel;
import com.ms.analise_tecnica_po.domain.defendant.repositories.DefendantRepository;
import com.ms.analise_tecnica_po.domain.process.models.ProcessModel;
import com.ms.analise_tecnica_po.domain.process.repositories.ProcessRepository;

import jakarta.persistence.EntityNotFoundException;

import org.slf4j.Logger;

@Service
public class AddDefendantUseCase {

  @Autowired
  private ProcessRepository processRepository;

  @Autowired
  private DefendantRepository defendantRepository;

  private static final Logger logger = LoggerFactory.getLogger(AddDefendantUseCase.class);

  public void execute(String processNumber, String defendantName) {

    logger.info("Executing AddDefendantUseCase for processNumber: {} and defendantName: {}", processNumber,
        defendantName);

    ProcessModel process = findbyProcessNumber(processNumber);

    if (process == null) {
      String errorMessage = "Processo não encontrado para o número: " + processNumber;
      logger.error(errorMessage);
      return;
    }

    DefendantModel newDefendant = new DefendantModel();
    newDefendant.setName(defendantName);

    process.addDefendant(newDefendant);

    defendantRepository.save(newDefendant);
    processRepository.save(process);

    logger.info("Defendant added successfully!");
  }

  public ProcessModel findbyProcessNumber(String processNumber) {
    logger.info("Finding process by processNumber: {}", processNumber);
    ProcessModel process = processRepository.findByProcessNumber(processNumber);
    logger.info("Found process: {}", process);
    return process;
  }
}

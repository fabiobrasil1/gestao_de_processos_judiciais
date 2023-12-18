package com.ms.analise_tecnica_po.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ms.analise_tecnica_po.controllers.dtos.process.AddDefendantDto;
import com.ms.analise_tecnica_po.controllers.dtos.process.ProcessDetailsRecordDto;
import com.ms.analise_tecnica_po.controllers.dtos.process.ProcessListDto;
import com.ms.analise_tecnica_po.controllers.dtos.process.ProcessRecordDto;
import com.ms.analise_tecnica_po.useCases.process.AddDefendantUseCase;
import com.ms.analise_tecnica_po.useCases.process.DeleteProcessUseCase;
import com.ms.analise_tecnica_po.useCases.process.FindProcessByIdUseCase;
import com.ms.analise_tecnica_po.useCases.process.GetProcessByUserUseCase;
import com.ms.analise_tecnica_po.useCases.process.RegisterProcessUseCase;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/process")
public class ProcessController {
  @Autowired
  private RegisterProcessUseCase registerProcessUC;
  @Autowired
  private FindProcessByIdUseCase findProcessByIdUC;
  @Autowired
  private GetProcessByUserUseCase getProcessByUserUC;
  @Autowired
  private DeleteProcessUseCase deleteProcessUC;
  @Autowired
  private AddDefendantUseCase addDefendantUseCase;

  @PostMapping
  @Transactional
  public ResponseEntity<?> register(
      @RequestBody @Valid ProcessRecordDto data,
      UriComponentsBuilder uriBuilder) {
    return registerProcessUC.execute(data, uriBuilder);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProcessDetailsRecordDto> findById(@PathVariable UUID id) {
    return findProcessByIdUC.execute(id);
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<ProcessListDto> retrieveUserProcesses(@PathVariable UUID userId) {
    return getProcessByUserUC.execute(userId);
  }

  @DeleteMapping("/{userId}/{processNumber}")
  public ResponseEntity<String> deleteProcessByNumberAndUser(
      @PathVariable UUID userId,
      @PathVariable String processNumber) {

    try {
      deleteProcessUC.execute(userId, processNumber);
      return ResponseEntity.noContent().build();
    } catch (EntityNotFoundException ex) {
      String errorMessage = ex.getMessage(); // Detalhe da exceção
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    } catch (Exception ex) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @PostMapping("/{processId}/defendants")
  public ResponseEntity<String> addDefendantToProcess(
      @PathVariable UUID processId,
      @Valid @RequestBody AddDefendantDto defendantRequestDto) {
    addDefendantUseCase.execute(processId, defendantRequestDto.defendantName());
    return ResponseEntity.ok("Réu adicionado com sucesso ao processo.");
  }
}

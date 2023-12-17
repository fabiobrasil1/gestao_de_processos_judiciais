package com.ms.analise_tecnica_po.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ms.analise_tecnica_po.controllers.dtos.process.ProcessDetailsRecordDto;
import com.ms.analise_tecnica_po.controllers.dtos.process.ProcessRecordDto;

import com.ms.analise_tecnica_po.useCases.process.FindProcessByIdUseCase;
import com.ms.analise_tecnica_po.useCases.process.RegisterProcessUseCase;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/process")
public class ProcessController {
  @Autowired
  private RegisterProcessUseCase registerProcessUC;
  @Autowired
  private FindProcessByIdUseCase findProcessByIdUC;

  @PostMapping
  @Transactional
  public ResponseEntity<ProcessDetailsRecordDto> register(
      @RequestBody @Valid ProcessRecordDto data,
      UriComponentsBuilder uriBuilder) {
    return registerProcessUC.execute(data, uriBuilder);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProcessDetailsRecordDto> findById(@PathVariable UUID id) {
    return findProcessByIdUC.execute(id);
  }
}

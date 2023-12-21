package com.ms.analise_tecnica_po.domain.process.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ms.analise_tecnica_po.domain.process.controllers.dtos.ProcessListDto;
import com.ms.analise_tecnica_po.domain.process.models.ProcessModel;
import com.ms.analise_tecnica_po.domain.process.repositories.ProcessRepository;
import com.ms.analise_tecnica_po.domain.user.models.UserModel;
import com.ms.analise_tecnica_po.domain.user.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class GetProcessByUserUseCase {
  @Autowired
  private ProcessRepository processRepository;

  @Autowired
  private UserRepository userRepository;

  public ResponseEntity<ProcessListDto> execute(UUID userId) {
    UserModel user = userRepository.findById(userId)
        .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + userId));

    List<String> processNumbers = processRepository.findByUser(user)
        .stream()
        .map(ProcessModel::getProcessNumber)
        .collect(Collectors.toList());

    ProcessListDto response = new ProcessListDto(processNumbers);

    return ResponseEntity.ok(response);
  }
}

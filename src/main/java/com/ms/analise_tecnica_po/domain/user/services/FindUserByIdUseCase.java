package com.ms.analise_tecnica_po.domain.user.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ms.analise_tecnica_po.domain.user.controllers.dtos.UserDetailsRecordDto;
import com.ms.analise_tecnica_po.domain.user.repositories.UserRepository;

@Service
public class FindUserByIdUseCase {
  @Autowired
  private UserRepository repository;

  public ResponseEntity<UserDetailsRecordDto> execute(UUID id) {
    try {
      var user = repository.getReferenceById(id);
      return ResponseEntity.ok(new UserDetailsRecordDto(user));
    } catch (Exception e) {
      throw e;
    }
  }
}

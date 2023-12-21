package com.ms.analise_tecnica_po.domain.user.controllers.dtos;

import java.util.UUID;

import com.ms.analise_tecnica_po.domain.user.models.UserModel;
import com.ms.analise_tecnica_po.domain.user.models.UserRole;

public record UserDetailsRecordDto(
    UUID id,
    String nome,
    UserRole role,
    String email) {

  public UserDetailsRecordDto(UserModel user) {
    this(
        user.getId(),
        user.getName(),
        user.getRole(),
        user.getEmail());
  }
}

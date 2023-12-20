package com.ms.analise_tecnica_po.controllers.dtos.users;

import java.util.UUID;

import com.ms.analise_tecnica_po.models.user.UserModel;
import com.ms.analise_tecnica_po.models.user.UserRole;

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

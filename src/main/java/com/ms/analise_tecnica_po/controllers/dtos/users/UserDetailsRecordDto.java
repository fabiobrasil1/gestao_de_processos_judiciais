package com.ms.analise_tecnica_po.controllers.dtos.users;

import java.util.UUID;

import com.ms.analise_tecnica_po.models.UserModel;

public record UserDetailsRecordDto(
    UUID id,
    String nome,
    String email) {

  public UserDetailsRecordDto(UserModel user) {
    this(
        user.getId(),
        user.getName(),
        user.getEmail());
  }
}
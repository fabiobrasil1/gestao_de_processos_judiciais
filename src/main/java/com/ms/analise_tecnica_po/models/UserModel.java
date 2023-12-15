package com.ms.analise_tecnica_po.models;

import java.util.UUID;

import com.ms.analise_tecnica_po.controllers.dtos.RegisterUserRecordDto;
import com.ms.analise_tecnica_po.controllers.dtos.UpdateUserRecordDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "user")
@Entity(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class UserModel {
  private static final long serialVersionUID = 1L;
  
  public UserModel(RegisterUserRecordDto user){
    this.name = user.name();
    this.email = user.email();
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID userId;
  private String name;
  private String email;

  public void updateUser(@Valid UpdateUserRecordDto dados){
    if (dados.name() != null) {
      this.name = dados.name();
    }
    if (dados.email() != null) {
      this.email = dados.email();
    }
  }
}

package com.ms.analise_tecnica_po.domain.user.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.ms.analise_tecnica_po.domain.user.controllers.dtos.RegisterUserRecordDto;
import com.ms.analise_tecnica_po.domain.user.controllers.dtos.UserDetailsRecordDto;
import com.ms.analise_tecnica_po.domain.user.models.UserModel;
import com.ms.analise_tecnica_po.domain.user.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class RegisterUserUseCase {
  @Autowired
  private UserRepository repository;
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Transactional
  public ResponseEntity<UserDetailsRecordDto> execute(RegisterUserRecordDto data, UriComponentsBuilder uriBuilder) {
    try {
      if (this.repository.findByEmail(data.email()) != null) {
        return ResponseEntity.badRequest().build();
      }

      String ecryptedPassword = new BCryptPasswordEncoder().encode(data.password());
      UserModel newUser = new UserModel(data.email(), ecryptedPassword, data.role(), data.name());
      this.repository.save(newUser);

      return ResponseEntity.ok().build();
    } catch (Exception e) {
      throw e;
    }
  }
}

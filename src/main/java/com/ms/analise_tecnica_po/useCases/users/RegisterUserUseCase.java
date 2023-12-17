package com.ms.analise_tecnica_po.useCases.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.ms.analise_tecnica_po.controllers.dtos.users.RegisterUserRecordDto;
import com.ms.analise_tecnica_po.controllers.dtos.users.UserDetailsRecordDto;
import com.ms.analise_tecnica_po.models.UserModel;
import com.ms.analise_tecnica_po.repositoies.UserRepository;

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
      var user = new UserModel(data);
      user.setPassword(passwordEncoder.encode(data.password()));
      repository.save(user);
      var uri = uriBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();

      return ResponseEntity.created(uri).body(new UserDetailsRecordDto(user));
    } catch (Exception e) {
      throw e;
    }
  }
}

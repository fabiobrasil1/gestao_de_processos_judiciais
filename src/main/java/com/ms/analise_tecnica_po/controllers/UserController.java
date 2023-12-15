package com.ms.analise_tecnica_po.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ms.analise_tecnica_po.controllers.dtos.UserDetailsRecordDto;
import com.ms.analise_tecnica_po.useCases.RegisterUserUseCase;
import com.ms.analise_tecnica_po.controllers.dtos.RegisterUserRecordDto;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
  @Autowired
  private RegisterUserUseCase registerUserUC;
  
  @PostMapping
  @Transactional
  public ResponseEntity<UserDetailsRecordDto> register(@RequestBody @Valid RegisterUserRecordDto data, UriComponentsBuilder uriBuilder) {
   return registerUserUC.execute(data, uriBuilder);
  }

  // @GetMapping("/{id}")
  // public ResponseEntity<UserDetailsRecordDto> findById (@PathVariable UUID id){
  //   return findUserByIdUC.execute(id);
    
  // }
}

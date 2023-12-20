package com.ms.analise_tecnica_po.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.analise_tecnica_po.controllers.dtos.auth.AuthDto;
import com.ms.analise_tecnica_po.controllers.dtos.auth.LoginResponseDto;
import com.ms.analise_tecnica_po.models.user.UserModel;
import com.ms.analise_tecnica_po.security.TokenService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
public class AuthController {
  @Autowired
  private AuthenticationManager manager;
  @Autowired
  private TokenService tokenService;

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody @Valid AuthDto data) {
    try {
      var token = new UsernamePasswordAuthenticationToken(data.email(), data.password());
      var authentication = this.manager.authenticate(token);

      var tokenJWT = tokenService.generateToken((UserModel) authentication.getPrincipal());

      return ResponseEntity.ok(new LoginResponseDto(tokenJWT));

    } catch (AuthenticationException e) {
      return ResponseEntity.status(401).body("Falha na autenticação: " + e.getMessage());
    }
  }
}
package com.ms.analise_tecnica_po.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.ms.analise_tecnica_po.models.UserModel;

@Service
public class TokenService {

  @Value("${security.token.secret}")
  private String secret;

  public String generateToken(UserModel user) {
    try {
      var algorithm = Algorithm.HMAC256(secret);
      return JWT.create()
          .withIssuer("analise_tecnica_po")
          .withClaim("id", user.getId().toString())
          .withSubject(user.getEmail())
          .withSubject(user.getName())
          .withExpiresAt(expiresDate())
          .sign(algorithm);
    } catch (JWTCreationException exception) {
      throw new RuntimeException("Erro ao gerar token", exception);
    }
  }

  public String getSubject(String tokenJWT) {
    try {
      var algorithm = Algorithm.HMAC256(secret);
      return JWT.require(algorithm)
          .withIssuer("analise_tecnica_po")
          .build()
          .verify(tokenJWT)
          .getSubject();
    } catch (JWTVerificationException exception) {
      throw new RuntimeException("Token inválido ou expirado!", exception);
    }
  };

  private Instant expiresDate() {
    return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
  }
};

package com.ms.analise_tecnica_po.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ms.analise_tecnica_po.domain.user.models.UserModel;

@Service
public class TokenService {

  @Value("${security.token.secret}")
  private String secret;

  public String generateToken(UserModel user) {
    try {
      System.out.println("Chave secreta usada para gerar o token: " + secret);

      var algorithm = Algorithm.HMAC256(secret);
      String token = JWT.create()
          .withIssuer("analise_tecnica_po")
          .withSubject(user.getRole().toString())
          .withClaim("id", user.getId().toString())
          .withClaim("name", user.getName())
          .withClaim("email", user.getEmail())
          .withExpiresAt(expiresDate())
          .sign(algorithm);

      System.out.println("Token gerado: " + token);

      return token;
    } catch (JWTCreationException exception) {
      throw new RuntimeException("Erro ao gerar token", exception);
    }
  }

  public String validationToken(String tokenJWT) {
    try {
      System.out.println("Token JWT recebido para validação: " + tokenJWT);

      var algorithm = Algorithm.HMAC256(secret);
      JWTVerifier verifier = JWT.require(algorithm)
          .withIssuer("analise_tecnica_po")
          .build();

      DecodedJWT jwt = verifier.verify(tokenJWT);

      String subject = jwt.getSubject();

      System.out.println("Token validado. Assunto: " + subject);

      return subject;
    } catch (JWTVerificationException exception) {
      System.out.println("Exceção na validação do token: " + exception.getMessage());
      exception.printStackTrace();
      return "Token inválido ou expirado!";
    }
  }

  public String getEmail(String tokenJWT) {
    try {
      System.out.println("Token JWT recebido para validação: " + tokenJWT);

      var algorithm = Algorithm.HMAC256(secret);
      JWTVerifier verifier = JWT.require(algorithm)
          .withIssuer("analise_tecnica_po")
          .build();

      DecodedJWT jwt = verifier.verify(tokenJWT);

      String email = jwt.getClaim("email").asString();

      System.out.println("Token validado. Email: " + email);

      return email;
    } catch (JWTVerificationException exception) {
      System.out.println("Exceção na validação do token: " + exception.getMessage());
      exception.printStackTrace();
      return "Email inválido!";
    }
  }

  private Instant expiresDate() {
    return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
  }
}

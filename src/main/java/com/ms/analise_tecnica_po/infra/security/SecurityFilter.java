package com.ms.analise_tecnica_po.infra.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ms.analise_tecnica_po.domain.user.models.UserRole;
import com.ms.analise_tecnica_po.domain.user.repositories.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
  private static final Logger logger = LoggerFactory.getLogger(SecurityFilter.class);

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private TokenService tokenService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    try {
      var tokenJWT = getTokenJWT(request);

      if (tokenJWT != null) {
        logger.info("Token JWT encontrado: '{}'", tokenJWT);

        var subject = tokenService.validationToken(tokenJWT);

        if (subject != null) {
          logger.info("Validação do token bem-sucedida. Assunto: '{}'", subject);

          String email = tokenService.getEmail(tokenJWT);
          UserDetails user = userRepository.findByEmail(email);

          if (user != null) {
            logger.info("Usuário encontrado: '{}'", user.getUsername());

            var authorities = user.getAuthorities();
            var authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
          } else {
            logger.warn("Usuário não encontrado para o assunto '{}'", subject);
          }
        } else {
          logger.warn("Falha na validação do token");
        }
      } else {
        logger.warn("Nenhum token JWT encontrado na solicitação");
      }
      filterChain.doFilter(request, response);
    } catch (Exception ex) {
      logger.error("Erro durante o processamento do filtro de segurança", ex);
      throw ex;
    }
  }

  private String getTokenJWT(HttpServletRequest request) {
    var authorizationHeader = request.getHeader("Authorization");
    if (authorizationHeader == null) {
      return null;
    }

    return authorizationHeader.replace("Bearer ", "");
  }
}

package com.ms.analise_tecnica_po.security;

import org.springframework.security.core.Authentication;

public interface AuthService {
  Authentication authenticate(String email, String password);
}
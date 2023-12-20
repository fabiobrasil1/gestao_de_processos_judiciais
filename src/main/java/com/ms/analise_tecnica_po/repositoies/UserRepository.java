package com.ms.analise_tecnica_po.repositoies;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.ms.analise_tecnica_po.models.user.UserModel;
import com.ms.analise_tecnica_po.models.user.UserRole;

public interface UserRepository extends JpaRepository<UserModel, UUID> {

  boolean existsByEmailIgnoreCase(String email);

  UserDetails findByRole(UserRole userRole);

  UserDetails findByEmail(String email);
}

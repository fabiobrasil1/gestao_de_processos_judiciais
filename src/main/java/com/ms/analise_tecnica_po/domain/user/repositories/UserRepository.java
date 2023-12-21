package com.ms.analise_tecnica_po.domain.user.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.ms.analise_tecnica_po.domain.user.models.UserModel;
import com.ms.analise_tecnica_po.domain.user.models.UserRole;

public interface UserRepository extends JpaRepository<UserModel, UUID> {

  boolean existsByEmailIgnoreCase(String email);

  UserDetails findByRole(UserRole userRole);

  UserDetails findByEmail(String email);
}

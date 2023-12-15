package com.ms.analise_tecnica_po.repositoies;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.analise_tecnica_po.models.UserModel;


public interface UserRepository extends JpaRepository<UserModel, UUID>{

  boolean existsByEmailIgnoreCase(String email);
  
}

package com.ms.analise_tecnica_po.repositoies;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.ms.analise_tecnica_po.models.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {

  boolean existsByEmailIgnoreCase(String email);

  UserModel findByEmail(String email);
}

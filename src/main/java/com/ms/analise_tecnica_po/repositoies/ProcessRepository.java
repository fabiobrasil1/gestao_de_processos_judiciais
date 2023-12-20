package com.ms.analise_tecnica_po.repositoies;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.ms.analise_tecnica_po.models.ProcessModel;
import com.ms.analise_tecnica_po.models.UserModel;

@Repository
public interface ProcessRepository extends JpaRepository<ProcessModel, UUID> {
  boolean existsByProcessNumberIgnoreCase(String processNumber);

  List<ProcessModel> findByUser(UserModel user);

  Optional<ProcessModel> findByUser_IdAndProcessNumberIgnoreCase(UUID userId, String processNumber);

}
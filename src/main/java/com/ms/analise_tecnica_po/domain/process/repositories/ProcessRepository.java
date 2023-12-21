package com.ms.analise_tecnica_po.domain.process.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.ms.analise_tecnica_po.domain.process.models.ProcessModel;
import com.ms.analise_tecnica_po.domain.user.models.UserModel;

public interface ProcessRepository extends JpaRepository<ProcessModel, UUID> {
  boolean existsByProcessNumberIgnoreCase(String processNumber);

  List<ProcessModel> findByUser(UserModel user);

  Optional<ProcessModel> findByUser_IdAndProcessNumberIgnoreCase(UUID userId, String processNumber);

  @Query("SELECT p FROM ProcessModel p WHERE p.processNumber = :processNumber")
  ProcessModel findByProcessNumber(@Param("processNumber") String processNumber);
}
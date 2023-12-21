package com.ms.analise_tecnica_po.domain.defendant.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.analise_tecnica_po.domain.defendant.models.DefendantModel;

public interface DefendantRepository extends JpaRepository<DefendantModel, UUID> {

}

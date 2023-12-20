package com.ms.analise_tecnica_po.repositoies;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.analise_tecnica_po.models.defendant.DefendantModel;

public interface DefendantRepository extends JpaRepository<DefendantModel, UUID> {

}

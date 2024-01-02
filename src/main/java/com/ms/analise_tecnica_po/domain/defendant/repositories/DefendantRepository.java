package com.ms.analise_tecnica_po.domain.defendant.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ms.analise_tecnica_po.domain.defendant.models.DefendantModel;

@Repository
public interface DefendantRepository extends JpaRepository<DefendantModel, UUID> {

}

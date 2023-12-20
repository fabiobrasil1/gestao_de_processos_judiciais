package com.ms.analise_tecnica_po.repositoies;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.ms.analise_tecnica_po.models.DefendantModel;

@Repository
public interface DefendantRepository extends JpaRepository<DefendantModel, UUID> {

}

package com.ms.analise_tecnica_po.domain.process.controllers.dtos;

import com.ms.analise_tecnica_po.domain.defendant.models.DefendantModel;

import jakarta.validation.constraints.NotBlank;

public record AddDefendantDto(
        @NotBlank(message = "O nome do réu não pode estar em branco") String defendantName) {

    public static AddDefendantDto fromProcessModel(DefendantModel defendantModel) {
        return new AddDefendantDto(defendantModel.getName());
    }
}
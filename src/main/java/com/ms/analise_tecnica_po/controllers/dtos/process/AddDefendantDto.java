package com.ms.analise_tecnica_po.controllers.dtos.process;

import com.ms.analise_tecnica_po.models.defendant.DefendantModel;

import jakarta.validation.constraints.NotBlank;

public record AddDefendantDto(
        @NotBlank(message = "O nome do réu não pode estar em branco") String defendantName) {

    public static AddDefendantDto fromProcessModel(DefendantModel defendantModel) {
        return new AddDefendantDto(defendantModel.getName());
    }
}
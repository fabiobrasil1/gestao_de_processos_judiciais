package com.ms.analise_tecnica_po.domain.process.controllers.dtos;

import java.util.List;

public class ProcessListDto {
  private List<String> processNumbers;

  public ProcessListDto(List<String> processNumbers) {
    this.processNumbers = processNumbers;
  }

  public List<String> getProcessNumbers() {
    return processNumbers;
  }
}
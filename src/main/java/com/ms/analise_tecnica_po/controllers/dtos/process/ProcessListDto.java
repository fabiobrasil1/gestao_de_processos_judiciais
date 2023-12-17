package com.ms.analise_tecnica_po.controllers.dtos.process;

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
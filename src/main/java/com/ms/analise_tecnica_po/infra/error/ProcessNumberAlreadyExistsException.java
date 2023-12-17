package com.ms.analise_tecnica_po.infra.error;

public class ProcessNumberAlreadyExistsException extends RuntimeException {

  public ProcessNumberAlreadyExistsException(String message) {
    super(message);
  }
}

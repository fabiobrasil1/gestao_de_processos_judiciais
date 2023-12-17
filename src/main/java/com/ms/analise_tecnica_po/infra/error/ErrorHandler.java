package com.ms.analise_tecnica_po.infra.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.auth0.jwt.exceptions.TokenExpiredException;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorHandler extends RuntimeException {

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ErrorResponse> notFound() {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Recurso não encontrado"));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<List<ErrorResponse>> badRequest(MethodArgumentNotValidException exception) {
    BindingResult bindingResult = exception.getBindingResult();
    List<ErrorResponse> errors = bindingResult.getFieldErrors()
        .stream()
        .map(ErrorResponse::fromFieldError)
        .collect(Collectors.toList());

    return ResponseEntity.badRequest().body(errors);
  }

  @ExceptionHandler(TokenExpiredException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public ResponseEntity<ErrorResponse> handleTokenExpiredException(TokenExpiredException ex) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse("Token expirado"));
  }

  @ExceptionHandler(ProcessNumberAlreadyExistsException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public ResponseEntity<ErrorResponse> handleProcessNumberAlreadyExistsException(
      ProcessNumberAlreadyExistsException ex) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse("Número do processo já existe"));
  }

  public static record ErrorResponse(String message) {
    public ErrorResponse {
    }

    public static ErrorResponse fromFieldError(FieldError error) {
      return new ErrorResponse(error.getDefaultMessage());
    }
  }
}

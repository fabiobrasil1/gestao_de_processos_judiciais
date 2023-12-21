package com.ms.analise_tecnica_po.domain.process.services;

import com.ms.analise_tecnica_po.domain.process.controllers.dtos.ProcessDetailsRecordDto;
import com.ms.analise_tecnica_po.domain.process.controllers.dtos.ProcessRecordDto;
import com.ms.analise_tecnica_po.domain.process.models.ProcessModel;
import com.ms.analise_tecnica_po.domain.process.repositories.ProcessRepository;
import com.ms.analise_tecnica_po.domain.user.models.UserModel;
import com.ms.analise_tecnica_po.domain.user.repositories.UserRepository;
import com.ms.analise_tecnica_po.infra.error.ErrorHandler;
import com.ms.analise_tecnica_po.infra.error.ProcessNumberAlreadyExistsException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class RegisterProcessUseCase {

    @Autowired
    private ProcessRepository processRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ErrorHandler errorHandler;

    @Transactional
    public ResponseEntity<?> execute(ProcessRecordDto data, UriComponentsBuilder uriBuilder) {
        try {
            UserModel user = userRepository.findById(data.userId())
                    .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + data.userId()));

            if (processRepository.existsByProcessNumberIgnoreCase(data.processNumber())) {
                return errorHandler.handleProcessNumberAlreadyExistsException(
                        new ProcessNumberAlreadyExistsException("O processo já existe"));
            }

            if (data.processNumber() == null) {
                throw new IllegalArgumentException("processNumber não pode ser nulo");
            }

            System.out.println("Valor de processNumber antes de salvar: " + data.processNumber());

            ProcessModel process = new ProcessModel(data);
            process.setUser(user);
            var processSaved = processRepository.save(process);
            System.out.println("Processo salvo: " + processSaved);

            var uri = uriBuilder.path("/process/{id}").buildAndExpand(process.getId()).toUri();

            return ResponseEntity.created(uri).body(ProcessDetailsRecordDto.fromProcessModel(process));
        } catch (Exception e) {
            System.out.println("Erro durante a execução do RegisterProcessUseCase: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

package com.ms.analise_tecnica_po.useCases.process;


import com.ms.analise_tecnica_po.controllers.dtos.process.ProcessDetailsRecordDto;
import com.ms.analise_tecnica_po.controllers.dtos.process.ProcessRecordDto;
import com.ms.analise_tecnica_po.models.ProcessModel;
import com.ms.analise_tecnica_po.repositoies.ProcessRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class RegisterProcessUseCase {

    @Autowired
    private ProcessRepository processRepository;

    @Transactional
    public ResponseEntity<ProcessDetailsRecordDto> execute(ProcessRecordDto data, UriComponentsBuilder uriBuilder) {
        try {
            var process = new ProcessModel(data);
            processRepository.save(process);
            var uri = uriBuilder.path("/process/{id}").buildAndExpand(process.getId()).toUri();

            return ResponseEntity.created(uri).body(ProcessDetailsRecordDto.fromProcessModel(process));
        } catch (Exception e) {
            throw e;
        }
    }
}
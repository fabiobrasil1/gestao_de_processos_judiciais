package com.ms.analise_tecnica_po.models.process;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.ms.analise_tecnica_po.controllers.dtos.process.ProcessRecordDto;
import com.ms.analise_tecnica_po.models.defendant.DefendantModel;
import com.ms.analise_tecnica_po.models.user.UserModel;

@Entity
@Table(name = "processes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProcessModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    @ManyToMany
    @JoinTable(name = "process_defendant", joinColumns = @JoinColumn(name = "process_id"), inverseJoinColumns = @JoinColumn(name = "defendant_id"))
    private Set<DefendantModel> defendants = new HashSet<>();

    @Column(name = "process_number", unique = true, nullable = false)
    private String processNumber;

    public ProcessModel(ProcessRecordDto dto) {
        this.description = dto.description();
        this.processNumber = dto.processNumber();
    }

    public void addDefendant(DefendantModel defendant) {
        this.defendants.add(defendant);
        defendant.getProcesses().add(this);
    }
}
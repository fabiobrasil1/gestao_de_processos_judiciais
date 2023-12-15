package com.ms.analise_tecnica_po.models;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.ms.analise_tecnica_po.controllers.dtos.users.RegisterUserRecordDto;
import com.ms.analise_tecnica_po.controllers.dtos.users.UpdateUserRecordDto;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProcessModel> processes = new HashSet<>();

    public UserModel(RegisterUserRecordDto user) {
        this.name = user.name();
        this.email = user.email();
    }

    public void updateUser(@Valid UpdateUserRecordDto data) {
        if (data.name() != null) {
            this.name = data.name();
        }
        if (data.email() != null) {
            this.email = data.email();
        }
    }

    public void addProcess(ProcessModel process) {
        this.processes.add(process);
        process.setUser(this);
    }

    public void removeProcess(ProcessModel process) {
        this.processes.remove(process);
        process.setUser(null);
    }
}

package com.ms.analise_tecnica_po.domain.defendant.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.ms.analise_tecnica_po.domain.process.models.ProcessModel;

@Entity
@Table(name = "defendants")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DefendantModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    @ManyToMany(mappedBy = "defendants")
    private Set<ProcessModel> processes = new HashSet<>();
}

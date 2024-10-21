package com.examenG7.examen.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name = "persona")
@Setter
@Getter
public class PersonaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long numDocu;
    private String nombres;
    private String apellidos;
    private String estado;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "direccion_id", referencedColumnName = "id")
    private DireccionEntity direccionEntity;

    @JsonManagedReference
    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL)
    private List<PedidoEntity> pedidos;
}

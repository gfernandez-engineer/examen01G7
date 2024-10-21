package com.examenG7.examen.entity;

import com.examenG7.examen.utils.Constantes;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pedido")
@Getter
@Setter
public class PedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
    private Integer cantidad;

    //@Enumerated(EnumType.STRING)
    @Column(name = "estado",nullable = false)
    private String estado;


    @ManyToOne
    @JoinColumn(name = "persona_id", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private PersonaEntity persona;

}

package com.examenG7.examen.repository;

import com.examenG7.examen.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<PedidoEntity,Long> {

    List<PedidoEntity> findByEstadoIn(List<String> estados);

    List<PedidoEntity> findByEstado(String estado);
}

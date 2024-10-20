package com.examenG7.examen.service;

import com.examenG7.examen.entity.PedidoEntity;
import com.examenG7.examen.entity.PersonaEntity;
import jakarta.transaction.Transactional;

import java.util.List;

public interface PedidoService {
    PedidoEntity crearPedido(Long clienteId, PedidoEntity pedido);
    List<PedidoEntity> buscarTodosPorEstados(List<String> estados);
    List<PedidoEntity> buscarPedidoPorParametro(Long id, String estado);
    PedidoEntity buscarPorId(Long id);
    List<PedidoEntity> buscarPorEstado(String estado);
    //@Transactional
    PedidoEntity actualizarPedido(Long id, PedidoEntity pedido);
    void eliminarPedido(Long id);
}

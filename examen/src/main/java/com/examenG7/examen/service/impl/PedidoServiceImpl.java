package com.examenG7.examen.service.impl;

import com.examenG7.examen.entity.PedidoEntity;
import com.examenG7.examen.entity.PersonaEntity;
import com.examenG7.examen.repository.PedidoRepository;
import com.examenG7.examen.repository.PersonaRepository;
import com.examenG7.examen.service.PedidoService;
import com.examenG7.examen.service.PersonaService;
import com.examenG7.examen.utils.Constantes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Supplier;

@Service
public class PedidoServiceImpl implements PedidoService {
    private final PersonaRepository personaRepository;
    private final PedidoRepository pedidoRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository, PersonaRepository personaRepository) {
        this.pedidoRepository = pedidoRepository;
        this.personaRepository = personaRepository;
    }

    @Override
    public PedidoEntity crearPedido(Long numDocu, PedidoEntity pedido) {
        PersonaEntity personaExistente = personaRepository.findAllByNumDocu(numDocu);
        if (personaExistente==null){
            throw new PersonaNotFoundException(numDocu);
        }
        pedido.setPersona(personaExistente);
        return pedidoRepository.save(pedido);
    }

    @Override
    public List<PedidoEntity> buscarTodosPorEstados(List<String> estados) {
        return pedidoRepository.findByEstadoIn(estados);
    }

    @Override
    public List<PedidoEntity> buscarPedidoPorParametro(Long id, String estado) {
        List<PedidoEntity> resultados = new ArrayList<>();
        // Buscar por ID
        if (id != null) {
            //pedidoRepository.findById(id).ifPresent(resultados::add);
            PedidoEntity pedidoPorId = pedidoRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Pedido No encontrado"));
            if (pedidoPorId != null) {
                resultados.add(pedidoPorId);
            }
        }
        // Buscar por Estado
        if (estado != null && !estado.isEmpty()) {
            List<PedidoEntity> pedidosPorEstado  = pedidoRepository.findByEstado(estado);
            resultados.addAll(pedidosPorEstado); // Agrega todos los pedidos encontrados
        }
        return resultados; // Devuelve la lista de resultados
    }

    @Override
    public PedidoEntity buscarPorId(Long id) {
        return pedidoRepository.findById(id).orElseThrow(()->new NoSuchElementException("Pedido no encontrado."));
    }

    @Override
    public List<PedidoEntity> buscarPorEstado(String estado) {
        return null;
    }

    @Override
    public PedidoEntity actualizarPedido(Long id, PedidoEntity pedidoActual) {
        PedidoEntity pedidoExistente = buscarPorId(id);
        pedidoExistente.setCantidad(pedidoActual.getCantidad());
        pedidoExistente.setEstado(pedidoActual.getEstado());
        return pedidoRepository.save(pedidoExistente);
    }

    @Override
    public void eliminarPedido(Long id) {
        PedidoEntity pedidoExistente = buscarPorId(id);
        pedidoExistente.setEstado(Constantes.EstadoPedido.ELIMINADO.getDescripcion());
        pedidoRepository.save(pedidoExistente);
    }
}

@ResponseStatus(HttpStatus.NOT_FOUND)
class PersonaNotFoundException extends RuntimeException{
    public PersonaNotFoundException(Long numDocu){
        super("Persona con el documento " + numDocu + " no fue encontrado");
    }
}
@ResponseStatus(HttpStatus.NOT_FOUND)
class ParametroNotFoundException extends RuntimeException{
    public ParametroNotFoundException(){
        super("Ningun parametro proporcionado");
    }
}

package com.examenG7.examen.service.impl;

import com.examenG7.examen.entity.DireccionEntity;
import com.examenG7.examen.entity.PedidoEntity;
import com.examenG7.examen.entity.PersonaEntity;
import com.examenG7.examen.repository.PersonaRepository;
import com.examenG7.examen.service.PersonaService;
import com.examenG7.examen.utils.Constantes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class PersonaSeviceImpl implements PersonaService {
    private final PersonaRepository personaRepository;

    public PersonaSeviceImpl(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Override
    public PersonaEntity crearPersona(PersonaEntity persona) {
        return personaRepository.save(persona);
    }

    @Override
    public List<PersonaEntity> buscarTodos() {
        return personaRepository.findAllByEstado(Constantes.EstadoPersona.ACTIVO.getDescripcion());
    }

    @Override
    public PersonaEntity buscarPersonaXnumDocumento(Long numDocu) {
        return personaRepository.findAllByNumDocu(numDocu);
    }


    @Override
    public PersonaEntity actualizarPersona(Long numDocu, PersonaEntity personaActual) {
        PersonaEntity personaExistente = buscarPersonaXnumDocumento(numDocu);
        personaExistente.setNombres(personaActual.getNombres());
        personaExistente.setApellidos(personaActual.getApellidos());
        personaExistente.setEstado(personaActual.getEstado());

        // Actualizar la dirección
        if (personaActual.getDireccionEntity() != null) {
            personaExistente.setDireccionEntity(personaActual.getDireccionEntity());
        }

        // Actualizar la lista de pedidos
        if (personaActual.getPedidos() != null) {
            personaExistente.getPedidos().clear();

            for (PedidoEntity pedido : personaActual.getPedidos()) {
                pedido.setPersona(personaExistente);
                personaExistente.getPedidos().add(pedido);
            }
        }

        return personaRepository.save(personaExistente);
    }

    @Override
    public void eliminarPersona(Long numDocu) {
        PersonaEntity personaExistente = buscarPersonaXnumDocumento(numDocu);
        personaExistente.setEstado(Constantes.EstadoPersona.INACTIVO.getDescripcion());
        personaRepository.save(personaExistente);

    }
}

package com.examenG7.examen.service;

import com.examenG7.examen.entity.PersonaEntity;

import java.util.List;

public interface PersonaService {
    PersonaEntity crearPersona(PersonaEntity persona);
    List<PersonaEntity> buscarTodos();
    PersonaEntity buscarPersonaXnumDocumento(Long numDocu);
    PersonaEntity actualizarPersona(Long id, PersonaEntity persona);
    void eliminarPersona(Long id);
}

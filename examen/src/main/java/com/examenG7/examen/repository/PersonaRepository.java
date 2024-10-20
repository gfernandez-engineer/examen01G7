package com.examenG7.examen.repository;

import com.examenG7.examen.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonaRepository extends JpaRepository<PersonaEntity,Long> {
    PersonaEntity findAllByNumDocu(Long numDocu);

    List<PersonaEntity> findAllByEstado(String estado);
}

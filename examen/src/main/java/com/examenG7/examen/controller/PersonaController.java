package com.examenG7.examen.controller;

import com.examenG7.examen.entity.PersonaEntity;
import com.examenG7.examen.service.PersonaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personas/v1")
public class PersonaController {
    private final PersonaService personaService;
    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @PostMapping("/crear")
    public ResponseEntity<PersonaEntity> crearPersona(@RequestBody PersonaEntity persona){
        PersonaEntity nuevaPersona = personaService.crearPersona(persona);
        return new ResponseEntity<>(nuevaPersona, HttpStatus.CREATED);
    }

    @GetMapping("/buscar")
    public List<PersonaEntity> buscarTodos(){
        return personaService.buscarTodos();
    }

    @GetMapping("/buscarXnumDocu/{numDocu}")
    public ResponseEntity<PersonaEntity> buscarPersonaXnumDocumento(@PathVariable Long numDocu){
        PersonaEntity persona = personaService.buscarPersonaXnumDocumento(numDocu);
        return new ResponseEntity<>(persona, HttpStatus.OK);
    }

    @PutMapping("/actualizar/{numDocu}")
    public ResponseEntity<PersonaEntity> actualizarPersona(@PathVariable Long numDocu, @RequestBody PersonaEntity persona){
        PersonaEntity personaActualizado = personaService.actualizarPersona(numDocu, persona);
        return new ResponseEntity<>(personaActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{numDocu}")
    public ResponseEntity<Void> eliminarPersona(@PathVariable Long numDocu){
        personaService.eliminarPersona(numDocu);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}


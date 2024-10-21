package com.examenG7.examen.controller;

import com.examenG7.examen.entity.PedidoEntity;
import com.examenG7.examen.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//NOMBRE: GIANCARLO FERNANDEZ INGA

@RestController
@RequestMapping("/pedidos/v1")
public class PedidoController  {
    private final PedidoService pedidoService;
    public PedidoController(PedidoService pedidoService){
        this.pedidoService = pedidoService;
    }

    @PostMapping("/crear/{numDocu}")
    public ResponseEntity<PedidoEntity> crearPedido(@PathVariable Long numDocu,
                                                    @RequestBody PedidoEntity pedido){
        PedidoEntity nuevoPedido = pedidoService.crearPedido(numDocu,pedido);
        return new ResponseEntity<>(nuevoPedido, HttpStatus.CREATED);
    }

    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<PedidoEntity> buscarPorId(@PathVariable Long id) {
        PedidoEntity pedido = pedidoService.buscarPorId(id);
        return new ResponseEntity<>(pedido, HttpStatus.OK);
    }


    @GetMapping("/buscarTodos")
    public List<PedidoEntity> buscarTodos(@RequestParam List<String> estados) {
        return pedidoService.buscarTodosPorEstados(estados);
    }

    @GetMapping("/buscarPedidoPorParametro")
    public ResponseEntity<List<PedidoEntity>> buscarPedidoPorParametro(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String estado) {

        List<PedidoEntity> pedidosEncontrados = pedidoService.buscarPedidoPorParametro(id, estado);

        if (!pedidosEncontrados.isEmpty()) {
            //return ResponseEntity.ok(pedidosEncontrados);
            return new ResponseEntity<>(pedidosEncontrados, HttpStatus.OK); // Usa el constructor de ResponseEntity
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Devuelve solo el estado 404
        }
    }


    @PutMapping("/actualizar/{id}")
    public ResponseEntity<PedidoEntity> actualizarPedido(@PathVariable Long id,
                                                         @RequestBody PedidoEntity pedido) {
        PedidoEntity pedidoActualizado = pedidoService.actualizarPedido(id, pedido);
        return new ResponseEntity<>(pedidoActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable Long id) {
        pedidoService.eliminarPedido(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

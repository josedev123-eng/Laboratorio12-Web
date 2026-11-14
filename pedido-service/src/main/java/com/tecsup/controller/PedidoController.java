package com.tecsup.controller;

import com.tecsup.model.Pedido;
import com.tecsup.services.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @GetMapping
    public List<Pedido> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obtener(@PathVariable Long id) {
        Pedido pedido = service.obtener(id);
        if (pedido == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedido);
    }

    @PostMapping
    public ResponseEntity<Pedido> guardar(@Valid @RequestBody Pedido pedido) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(pedido));
    }

    @PutMapping("/{id}/anular")
    public ResponseEntity<Pedido> anular(@PathVariable Long id) {
        return respuesta(service.cambiarEstado(id, "ANULADO"));
    }

    @PutMapping("/{id}/pendiente")
    public ResponseEntity<Pedido> pendiente(@PathVariable Long id) {
        return respuesta(service.cambiarEstado(id, "PENDIENTE"));
    }

    @PutMapping("/{id}/atender")
    public ResponseEntity<Pedido> atender(@PathVariable Long id) {
        return respuesta(service.cambiarEstado(id, "ATENDIDO"));
    }

    private ResponseEntity<Pedido> respuesta(Pedido pedido) {
        if (pedido == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedido);
    }
}

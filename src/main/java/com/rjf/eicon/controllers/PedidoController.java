package com.rjf.eicon.controllers;

import com.rjf.eicon.dtos.PedidoDto;
import com.rjf.eicon.dtos.PedidosDto;
import com.rjf.eicon.model.Pedido;
import com.rjf.eicon.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Void> addPedidos(@RequestBody @Validated PedidosDto pedidos) {
        pedidoService.addPedidos(pedidos);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<PedidosDto> getPedidos() {
        return ResponseEntity.ok(pedidoService.getPedidos());
    }

    @GetMapping("/{controle}/controle")
    public ResponseEntity<PedidoDto> getPedidosByControle(@PathVariable Long controle) {
        return ResponseEntity.ok(pedidoService.getPedido(controle));
    }

    @GetMapping("/{data}/data")
    public ResponseEntity<PedidosDto> getPedidosByData(@PathVariable String data) {
        return ResponseEntity.ok(pedidoService.getPedidos(data));
    }
}

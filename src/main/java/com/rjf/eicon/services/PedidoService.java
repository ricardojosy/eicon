package com.rjf.eicon.services;

import com.rjf.eicon.dtos.PedidoDto;
import com.rjf.eicon.dtos.PedidosDto;
import com.rjf.eicon.model.Pedido;
import com.rjf.eicon.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    public void addPedidos(PedidosDto lista) {
        lista.pedidos().forEach(pedido -> repository.save(pedido.toPedido()));
    }

    public PedidosDto getPedidos() {
        List<Pedido> pedidos = repository.findAll();
        return new PedidosDto(pedidos.stream().map(PedidoDto::toDto).toList());
    }

    public PedidoDto getPedido(Long controle) {
        Optional<Pedido> pedido = repository.findByNumeroControle(controle);
        if (pedido.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return PedidoDto.toDto(pedido.get());
    }

    public PedidosDto getPedidos(String data) {
        String dataInicio = data + " 00:00:00";
        String dataFim = data + " 23:59:59";
        List<Pedido> pedidos = repository.findByDatas(dataInicio, dataFim);
        return new PedidosDto(pedidos.stream().map(PedidoDto::toDto).toList());
    }
}
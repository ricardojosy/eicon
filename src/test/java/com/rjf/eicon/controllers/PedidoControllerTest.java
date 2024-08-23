package com.rjf.eicon.controllers;

import com.rjf.eicon.dtos.PedidoDto;
import com.rjf.eicon.dtos.PedidosDto;
import com.rjf.eicon.services.PedidoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalTime.now;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

public class PedidoControllerTest {

    @Mock
    private PedidoService pedidoService;

    @InjectMocks
    private PedidoController pedidoController;

    private PedidosDto pedidosDto;
    private PedidoDto pedidoDto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        pedidoDto = new PedidoDto(200L, Instant.now(), "produto teste 200", BigDecimal.valueOf(8.5), 3, 2L, BigDecimal.valueOf(25.5));
        List<PedidoDto> dtos = new ArrayList<>();
        dtos.add(pedidoDto);
        pedidosDto = new PedidosDto(dtos);
    }

    @Test
    public void testAddPedidos() {
        ResponseEntity<Void> response = pedidoController.addPedidos(pedidosDto);

        assertEquals(200, response.getStatusCodeValue());
        verify(pedidoService).addPedidos(pedidosDto);
    }

    @Test
    public void testGetPedidos() {
        when(pedidoService.getPedidos()).thenReturn(pedidosDto);

        ResponseEntity<PedidosDto> response = pedidoController.getPedidos();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(pedidosDto, response.getBody());

        verify(pedidoService).getPedidos();
    }

    @Test
    public void testGetPedidosByControle() {
        Long controle = 1L;
        when(pedidoService.getPedido(controle)).thenReturn(pedidoDto);

        ResponseEntity<PedidoDto> response = pedidoController.getPedidosByControle(controle);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(pedidoDto, response.getBody());

        verify(pedidoService).getPedido(controle);
    }

    @Test
    public void testGetPedidosByData() {
        String data = "2023-08-22";
        when(pedidoService.getPedidos(data)).thenReturn(pedidosDto);

        ResponseEntity<PedidosDto> response = pedidoController.getPedidosByData(data);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(pedidosDto, response.getBody());

        verify(pedidoService).getPedidos(data);
    }
}

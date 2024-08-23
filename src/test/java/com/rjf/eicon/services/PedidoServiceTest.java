
package com.rjf.eicon.services;

import com.rjf.eicon.dtos.PedidoDto;
import com.rjf.eicon.dtos.PedidosDto;
import com.rjf.eicon.model.Pedido;
import com.rjf.eicon.repositories.PedidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PedidoServiceTest {

    @Mock
    private PedidoRepository repository;

    @InjectMocks
    private PedidoService pedidoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddPedidos() {
        PedidosDto pedidosDto = mock(PedidosDto.class);
        Pedido pedido = mock(Pedido.class);
        when(repository.save(any(Pedido.class))).thenReturn(pedido);

        pedidoService.addPedidos(pedidosDto);
    }

    @Test
    public void testGetPedidos() {
        Pedido pedido = mock(Pedido.class);
        when(repository.findAll()).thenReturn(List.of(pedido));
        PedidosDto expectedDto = new PedidosDto(List.of(PedidoDto.toDto(pedido)));

        PedidosDto actualDto = pedidoService.getPedidos();

        assertEquals(expectedDto, actualDto);

        verify(repository, times(1)).findAll();
    }

    @Test
    public void testGetPedido_Success() {
        Long controle = 1L;
        Pedido pedido = mock(Pedido.class);
        when(repository.findByNumeroControle(controle)).thenReturn(Optional.of(pedido));
        PedidoDto expectedDto = PedidoDto.toDto(pedido);

        PedidoDto actualDto = pedidoService.getPedido(controle);

        assertEquals(expectedDto, actualDto);

        verify(repository, times(1)).findByNumeroControle(controle);
    }

    @Test
    public void testGetPedido_NotFound() {
        Long controle = 1L;
        when(repository.findByNumeroControle(controle)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> pedidoService.getPedido(controle));

        verify(repository, times(1)).findByNumeroControle(controle);
    }

    @Test
    public void testGetPedidosByData() {

        String data = "2023-08-22";
        Pedido pedido = mock(Pedido.class);
        when(repository.findByDatas(anyString(), anyString())).thenReturn(List.of(pedido));
        PedidosDto expectedDto = new PedidosDto(List.of(PedidoDto.toDto(pedido)));

        PedidosDto actualDto = pedidoService.getPedidos(data);

        assertEquals(expectedDto, actualDto);

        verify(repository, times(1)).findByDatas(anyString(), anyString());
    }
}

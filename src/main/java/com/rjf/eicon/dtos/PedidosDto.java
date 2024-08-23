package com.rjf.eicon.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.util.List;

public record PedidosDto(
        @Size(min=1, max=10)
        List<PedidoDto> pedidos
) {

}

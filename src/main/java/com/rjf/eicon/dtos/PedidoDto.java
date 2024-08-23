package com.rjf.eicon.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rjf.eicon.model.Pedido;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.Instant;

public record PedidoDto(
        @NotNull
        Long numeroControle,
        @CreationTimestamp
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
        Instant dataCadastro,
        @NotBlank
        String nomeProduto,
        @NotNull
        BigDecimal valorUnitario,
        Integer quantidade,
        @NotNull
        Long codigoCliente,
        BigDecimal valorTotal
) {

    public static PedidoDto toDto(Pedido pedido) {
        return new PedidoDto(
                pedido.getNumeroControle(),
                pedido.getDataCadastro(),
                pedido.getNomeProduto(),
                pedido.getValorUnitario(),
                pedido.getQuantidade(),
                pedido.getCodigoCliente(),
                pedido.getValorTotal());
    }

    public Pedido toPedido() {
        Integer qtd = quantidade;
        BigDecimal valorTotal = valorUnitario;
        if (quantidade == null || quantidade == 0) {
            qtd = 1;
        } else if (qtd > 9) {
            valorTotal = valorTotal.multiply(BigDecimal.valueOf(qtd)).multiply(BigDecimal.valueOf(0.90));
        } else if (qtd > 4) {
            valorTotal = valorTotal.multiply(BigDecimal.valueOf(qtd)).multiply(BigDecimal.valueOf(0.95));
        } else {
            valorTotal = valorTotal.multiply(BigDecimal.valueOf(qtd));
        }
        return new Pedido(
                this.numeroControle,
                this.dataCadastro,
                this.nomeProduto,
                this.valorUnitario,
                qtd,
                this.codigoCliente,
                valorTotal);
    }
}

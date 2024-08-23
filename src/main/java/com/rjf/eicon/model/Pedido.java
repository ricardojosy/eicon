package com.rjf.eicon.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long numeroControle;

    @CreationTimestamp
    private Instant dataCadastro;

    @NotBlank
    private String nomeProduto;

    @NotNull
    private BigDecimal valorUnitario;

    @Column(columnDefinition="default '1'")
    private Integer quantidade;

    @NotNull
    private Long codigoCliente;

    @NotNull
    private BigDecimal valorTotal;

    public Pedido(Long numeroControle, Instant dataCadastro, String nomeProduto, BigDecimal valorUnitario, Integer quantidade, Long codigoCliente, BigDecimal valorTotal) {
        this.numeroControle = numeroControle;
        this.dataCadastro = dataCadastro;
        this.nomeProduto = nomeProduto;
        this.valorUnitario = valorUnitario;
        this.quantidade = quantidade;
        this.codigoCliente = codigoCliente;
        this.valorTotal= valorTotal;
    }
}

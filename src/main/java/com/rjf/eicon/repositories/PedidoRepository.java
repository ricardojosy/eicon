package com.rjf.eicon.repositories;

import com.rjf.eicon.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findAllByOrderById();

    Optional<Pedido> findByNumeroControle(Long controle);

    @Query(value="SELECT * FROM pedidos p WHERE p.data_cadastro BETWEEN :dataInicio AND :dataFim", nativeQuery = true)
    List<Pedido> findByDatas(String dataInicio, String dataFim);
}
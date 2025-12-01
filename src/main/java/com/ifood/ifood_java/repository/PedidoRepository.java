package com.ifood.ifood_java.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.ifood.ifood_java.modal.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}

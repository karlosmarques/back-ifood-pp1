// package com.ifood.ifood_java.service.pagamento;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;

//import com.ifood.ifood_java.entity.pedido.Pedido;
//import com.ifood.ifood_java.repository.PedidoRepository;

//@Service
//public class PagamentoFakeService {

    //@Autowired
    //private PedidoRepository pedidoRepository;

    //public Pedido pagarPedido(Long pedidoId) {

        //Pedido pedido = pedidoRepository.findById(pedidoId)
               // .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        // simula pagamento aprovado
        //pedido.setPagamentoStatus("APROVADO");
        //pedido.setStatus("PAGO");
        //pedido.setMetodoPagamento("FAKE");

        //return pedidoRepository.save(pedido);
   // }

    //public Pedido cancelarPagamento(Long pedidoId) {

        //Pedido pedido = pedidoRepository.findById(pedidoId)
               // .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        //pedido.setPagamentoStatus("RECUSADO");
        //pedido.setStatus("CANCELADO");

       // return pedidoRepository.save(pedido);
    //}
//}       

package com.ifood.ifood_java.controller.pagamento;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ifood.ifood_java.entity.pedido.Pedido;
import com.ifood.ifood_java.repository.PedidoRepository;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.resources.payment.Payment;

@RestController
@RequestMapping("/pagamentos/mercadopago")
public class MercadoPagoWebhookController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @PostMapping("/webhook")
    public void receberNotificacao(
            @RequestParam(required = false) String type,
            @RequestBody Map<String, Object> payload) throws Exception {

        if (!"payment".equals(type)) return;

        Map<String, Object> data = (Map<String, Object>) payload.get("data");
        String paymentId = data.get("id").toString();

        PaymentClient client = new PaymentClient();

        Payment payment = client.get(Long.valueOf(paymentId));

        Long pedidoId = Long.valueOf(payment.getExternalReference());
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        pedido.setMercadopagoPaymentId(paymentId);
        pedido.setPagamentoStatus(payment.getStatus().toUpperCase());

        if ("approved".equalsIgnoreCase(payment.getStatus())) {
            pedido.setStatus("PAGO");
        }

        pedidoRepository.save(pedido);
    }
}

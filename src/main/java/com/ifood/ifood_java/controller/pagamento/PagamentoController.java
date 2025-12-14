package com.ifood.ifood_java.controller.pagamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import com.ifood.ifood_java.service.pagamento.MercadoPagoService;
import com.mercadopago.resources.preference.Preference;

@RestController
@RequestMapping("/pagamentos")
@CrossOrigin
public class PagamentoController {

    @Autowired
    private MercadoPagoService mercadoPagoService;

    @PostMapping("/mercadopago/preference/{pedidoId}")
    public ResponseEntity<?> criarPreference(
            @PathVariable Long pedidoId,
            @RequestParam BigDecimal valorTotal) throws Exception {

        Preference preference = mercadoPagoService.criarPreference(
                pedidoId,
                "Pagamento do pedido #" + pedidoId,
                valorTotal
        );

        return ResponseEntity.ok(preference);
    }
}

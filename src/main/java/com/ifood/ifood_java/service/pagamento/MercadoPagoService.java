package com.ifood.ifood_java.service.pagamento;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.resources.preference.Preference;

@Service
public class MercadoPagoService {

    @Value("${mercadopago.notification-url}")
    private String notificationUrl;

    public Preference criarPreference(Long pedidoId, String descricao, BigDecimal valorTotal) throws Exception {

        PreferenceItemRequest item = PreferenceItemRequest.builder()
                .title(descricao)
                .quantity(1)
                .currencyId("BRL")
                .unitPrice(valorTotal)
                .build();

        PreferenceRequest request = PreferenceRequest.builder()
                .items(List.of(item))
                .externalReference(String.valueOf(pedidoId))
                .notificationUrl(notificationUrl)
                .build();

        PreferenceClient client = new PreferenceClient();
        return client.create(request);
    }
}

package com.example.demo.controller;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.resources.preference.Preference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pagos")
@CrossOrigin(origins = "*") 
public class PagoController {

    public PagoController() {
        // Tu Access Token de Prueba de Mercado Pago (lo cambiaremos luego)
        MercadoPagoConfig.setAccessToken("TEST-AQUI_VA_TU_ACCESS_TOKEN");
    }

    @PostMapping("/crear-preferencia")
    public ResponseEntity<String> crearPreferencia(@RequestBody Map<String, Object> request) {
        try {
            String titulo = (String) request.get("titulo");
            BigDecimal precio = new BigDecimal(request.get("precio").toString());

            PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
                    .title(titulo)
                    .quantity(1)
                    .unitPrice(precio)
                    .currencyId("MXN")
                    .build();

            List<PreferenceItemRequest> items = new ArrayList<>();
            items.add(itemRequest);

            PreferenceBackUrlsRequest backUrls = PreferenceBackUrlsRequest.builder()
                    .success("https://tu-usuario.github.io/exito.html")
                    .failure("https://tu-usuario.github.io/fallo.html")
                    .pending("https://tu-usuario.github.io/pendiente.html")
                    .build();

            PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                    .items(items)
                    .backUrls(backUrls)
                    .autoReturn("approved")
                    .build();

            PreferenceClient client = new PreferenceClient();
            Preference preference = client.create(preferenceRequest);

            return ResponseEntity.ok(preference.getInitPoint());

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al crear la preferencia: " + e.getMessage());
        }
    }
}
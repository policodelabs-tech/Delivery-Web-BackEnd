package com.example.demo.bot;

import com.microsoft.bot.builder.ActivityHandler;
import com.microsoft.bot.builder.MessageFactory;
import com.microsoft.bot.builder.TurnContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class DeliveryBot extends ActivityHandler {

    @Override
    protected CompletableFuture<Void> onMessageActivity(TurnContext turnContext) {
        // Aquí capturamos lo que el usuario escribió
        String textoUsuario = turnContext.getActivity().getText();

        // TODO: Aquí conectaremos con la API de Claude Sonnet enviando 'textoUsuario'
        
        // Respuesta temporal de prueba para validar la conexión
        String respuesta = "Hola, soy el nuevo motor de Delivery In-Transit. Recibí tu mensaje: " + textoUsuario;
        
        return turnContext.sendActivity(MessageFactory.text(respuesta))
                .thenApply(sendResult -> null);
    }
}
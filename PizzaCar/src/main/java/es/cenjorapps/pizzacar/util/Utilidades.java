package es.cenjorapps.pizzacar.util;

import java.util.UUID;

public class Utilidades {

    public static String generateConfirmationToken() {        
        return UUID.randomUUID().toString();
    }
}
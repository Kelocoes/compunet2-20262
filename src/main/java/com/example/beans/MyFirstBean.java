package com.example.beans;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("myFirstBean")
@Scope("singleton")
public class MyFirstBean {

    private String mensaje = "Hola desde Spring con Anotaciones! - Proyecto Fitness App";

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}


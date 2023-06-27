package com.example.pedido_api.domain;

import java.util.UUID;

public class Pedido {
    private UUID id;
    private Int numero;
    private String cliente;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(Int numero) {
        this.numero = numero;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Pedido() {
        this.id = UUID.randomUUID(); // generate a new UUID-v4
        this.numero = 0;
        this.cliente = "";
    }

    public Pedido(UUID id, Int numero, String cliente) {
        this.id = id;
        this.numero = numero;
        this.cliente = cliente;
    }
}
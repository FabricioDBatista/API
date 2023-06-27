package com.example.pedido_api.service.exception;

public class ClientePedidoObrigatorioException extends RuntimeException {

    public ClientePedidoObrigatorioException() {
        super("O cliente do pedido é campo obrigatorio");
    }
}
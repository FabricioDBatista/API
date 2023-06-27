package com.example.pedido_api.service.exception;

public class NumeroPedidoObrigatorioException extends RuntimeException {

    public NumeroPedidoObrigatorioException() {
        super("O numero do pedido é campo obrigatorio");
    }
}
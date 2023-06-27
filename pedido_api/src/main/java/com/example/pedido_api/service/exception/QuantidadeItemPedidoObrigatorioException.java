package com.example.pedido_api.service.exception;

public class QuantidadeItemPedidoObrigatorioException extends RuntimeException {

    public QuantidadeItemPedidoObrigatorioException() {
        super("A quantidade do produto é campo obrigatorio");
    }
}
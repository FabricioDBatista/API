package com.example.pedido_api.service.exception;

public class PrecoItemPedidoObrigatorioException extends RuntimeException {

    public PrecoItemPedidoObrigatorioException() {
        super("O preço do produto é campo obrigatorio");
    }
}
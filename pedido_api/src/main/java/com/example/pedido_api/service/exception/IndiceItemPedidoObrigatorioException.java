package com.example.pedido_api.service.exception;

public class IndiceItemPedidoObrigatorioException extends RuntimeException {

    public IndiceItemPedidoObrigatorioException() {
        super("O indice do item do pedido é campo obrigatorio");
    }
}
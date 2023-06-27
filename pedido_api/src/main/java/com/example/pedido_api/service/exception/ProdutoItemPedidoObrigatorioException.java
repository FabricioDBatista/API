package com.example.pedido_api.service.exception;

public class ProdutoItemPedidoObrigatorioException extends RuntimeException {

    public ProdutoItemPedidoObrigatorioException() {
        super("O produto do item do pedido é campo obrigatorio");
    }
}
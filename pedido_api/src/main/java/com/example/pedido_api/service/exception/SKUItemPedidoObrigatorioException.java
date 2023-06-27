package com.example.pedido_api.service.exception;

public class SKUItemPedidoObrigatorioException extends RuntimeException {

    public SKUItemPedidoObrigatorioException() {
        super("O SKU do produto é campo obrigatorio");
    }
}
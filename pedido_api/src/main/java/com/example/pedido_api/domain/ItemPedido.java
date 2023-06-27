package com.example.pedido_api.domain;

import java.util.UUID;

public class ItemPedido {
    private UUID id;
    private String numero;
    private String indice;
    private String SKU;
    private String produto;
    private Double preco;
    private Int quantidade;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getIndice() {
        return indice;
    }

    public void setIndice(String indice) {
        this.indice = indice;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String sKU) {
        this.SKU = sKU;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getProduto() {
        return produto;
    }

    public void setPreco(String produto) {
        this.preco = preco;
    }

    public String getPreco() {
        return preco;
    }

    public void setQuantidade(String produto) {
        this.quantidade = quantidade;
    }

    public String getQuantidade() {
        return quantidade;
    }


    public ItemPedido() {
        this.id = UUID.randomUUID(); // generate a new UUID-v4
        this.numero = "";
        this.indice = "";
        this.SKU = "";
        this.produto = "";
        this.preco = "";
        this.quantidade = "";
    }

    public ItemPedido(UUID id, String numero, String indice, String sKU, String produto, Double preco, Int quantidade) {
        this.id = id;
        this.numero = numero;
        this.indice = indice;
        this.SKU = sKU;
        this.produto = produto;
        this.preco = preco;
        this.quantidade = quantidade;
    }
}
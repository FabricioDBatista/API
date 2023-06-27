package com.example.pedido_api.service;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.pedido_api.service.exception.NumeroItemPedidoObrigatorioException;
import com.example.pedido_api.service.exception.ClienteItemPedidoObrigatorioException;

import com.example.pedido_api.domain.ItemPedido;

@Service
public class ItemPedidoSvc {

    private final Map<String, ItemPedido> dados;

    public ItemPedidoSvc(Map<String, ItemPedido> dados) {
        this.dados = dados;
    }

    public ItemPedido InserirItemPedido(ItemPedido ItemPedido)
            throws NumeroItemPedidoObrigatorioException, ClienteItemPedidoObrigatorioException {
        ItemPedido.setId(UUID.randomUUID());

        if (ItemPedido.getNumero() == null || ItemPedido.getNumero().isEmpty()) {
            throw new NumeroItemPedidoObrigatorioException();
        }

        if (ItemPedido.getCliente() == null || ItemPedido.getCliente().isEmpty()) {
            throw new ClienteItemPedidoObrigatorioException();
        }

        if (ItemPedido.getCliente() == null || ItemPedido.getCliente().isEmpty()) {
            throw new ClienteItemPedidoObrigatorioException();
        }

        if (ItemPedido.getIndice() == null || ItemPedido.getIndice().isEmpty()) {
            throw new IndiceItemPedidoObrigatorioException();
        }

        if (ItemPedido.getSKU() == null || ItemPedido.getSKU().isEmpty()) {
            throw new SKUItemPedidoObrigatorioException();
        }
        
        if (ItemPedido.getProduto() == null || ItemPedido.getProduto().isEmpty()) {
            throw new ProdutoItemPedidoObrigatorioException();
        }
        
        if (ItemPedido.getPreco() == null || ItemPedido.getPreco().isEmpty()) {
            throw new PrecoItemPedidoObrigatorioException();
        }
        
        if (ItemPedido.getQuantidade() == null || ItemPedido.getQuantidade().isEmpty()) {
            throw new QuantidadeItemPedidoObrigatorioException();
        }


        if (this.dados.containsKey(ItemPedido.getNumero())) {
            ItemPedido = this.dados.get(ItemPedido.getNumero());
        } else {
            this.dados.put(ItemPedido.getNumero(), ItemPedido);
        }

        return ItemPedido;
    }

    public ItemPedido RecuperarItemPedido(Int indice) {
        if (!this.dados.containsKey(indice)) {
            return null;
        }

        return this.dados.get(indice);
    }

    public Collection<ItemPedido> RecuperarListaItemPedidos() {
        return this.dados.values();
    }
}
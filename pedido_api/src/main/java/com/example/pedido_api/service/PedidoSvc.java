package com.example.pedido_api.service;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.pedido_api.service.exception.NumeroPedidoObrigatorioException;
import com.example.pedido_api.service.exception.ClientePedidoObrigatorioException;

import com.example.pedido_api.domain.Pedido;

@Service
public class PedidoSvc {

    private final Map<String, Pedido> dados;

    public PedidoSvc(Map<String, Pedido> dados) {
        this.dados = dados;
    }

    // metodo para inserir um novo Pedido
    public Pedido InserirPedido(Pedido Pedido)
            throws NumeroPedidoObrigatorioException, ClientePedidoObrigatorioException {
        Pedido.setId(UUID.randomUUID());

        if (Pedido.getNumero() == null || Pedido.getNumero().isEmpty()) {
            throw new NumeroPedidoObrigatorioException();
        }

        if (Pedido.getCliente() == null || Pedido.getCliente().isEmpty()) {
            throw new ClientePedidoObrigatorioException();
        }

        // implementacao idempotente
        if (this.dados.containsKey(Pedido.getNumero())) {
            Pedido = this.dados.get(Pedido.getNumero());
        } else {
            this.dados.put(Pedido.getNumero(), Pedido);
        }

        return Pedido;
    }

    // metodo para buscar um Pedido pelo numero
    public Pedido RecuperarPedido(Int numero) {
        if (!this.dados.containsKey(numero)) {
            return null;
        }

        return this.dados.get(numero);
    }

    // metodo para retornar uma lista com todos os Pedidos
    public Collection<Pedido> RecuperarListaPedidos() {
        return this.dados.values();
    }
}
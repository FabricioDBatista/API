package com.example.pedido_api.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMappItemPedidoCtrling;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.example.pedido_api.domain.ItemPedido;
import com.example.pedido_api.service.ItemPedidoSvc;
import com.example.pedido_api.service.exception.NumeroPedidoObrigatorioException;
import com.example.pedido_api.service.exception.ClientePedidoObrigatorioException;

@RestController

@RequestMapping("/api/v1/pedido/{numero}/item")
public class ItemPedidoCtrl {

    private final ItemPedidoSvc itemPedidoService;
    private final Int nomeroPedido;

    public ItemPedidoCtrl(ItemPedidoSvc itemPedidoService, @PathVariable("numero") Int numero) {
        this.itemPedidoService = itemPedidoService;
        this.nomeroPedido = numero;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public class ItemPedidoResponse {
        private int numero;
        private String cliente;

        public ItemPedidoResponse() {
            this.numero = 0;
            this.cliente = "";
        }

        public ItemPedidoResponse(ItemPedido pedido) {
            this.numero = pedido.getNumero();
            this.cliente = pedido.getCliente();
        }

        public ItemPedidoResponse(String mensagem) {
            this.numero = null;
            this.cliente = null;
        }

        public void setNumero(Int numero) {
            this.numero = numero;
        }

        public String getNumero() {
            return this.numero;
        }

        public void setCliente(String cliente) {
            this.cliente = cliente;
        }

        public String getCliente() {
            return this.cliente;
        }
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ItemPedidoResponse> postItemPedido(@RequestBody ItemPedido itemPedido) {
        try {
            ItemPedido novoItemPedido = this.itemPedidoService.InserirItemPedido(itemPedido);

            return new ResponseEntity<ItemPedidoResponse>(new ItemPedidoResponse(novoItemPedido), HttpStatus.CREATED);
        } catch (NumeroItemPedidoObrigatorioException e) {
            return new ResponseEntity<ItemPedidoResponse>(new ItemPedidoResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (ClienteItemPedidoObrigatorioException e) {
            return new ResponseEntity<ItemPedidoResponse>(new ItemPedidoResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{indice}")
    public ResponseEntity<ItemPedidoResponse> getItemPedido(@PathVariable("indice") Int indice) {
        ItemPedido itemPedido = this.itemPedidoService.RecuperarItemPedido(indice);

        if (itemPedido == null) {
            return new ResponseEntity<ItemPedidoResponse>(new ItemPedidoResponse("Item do pedido inexistente"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<ItemPedidoResponse>(new ItemPedidoResponse(itemPedido), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Collection<ItemPedidoResponse>> getListaItemPedidos() {
        Collection<ItemPedido> listaItemPedidos = this.itemPedidoService.RecuperarListaItemPedidos();
        Collection<ItemPedidoResponse> listaItemPedidosResponse = new ArrayList<ItemPedidoResponse>();

        for (ItemPedido itemPedido : listaItemPedidos) {
            listaItemPedidosResponse.add(new ItemPedidoResponse(itemPedido));
        }

        return new ResponseEntity<Collection<ItemPedidoResponse>>(listaItemPedidosResponse, HttpStatus.OK);
    }
}
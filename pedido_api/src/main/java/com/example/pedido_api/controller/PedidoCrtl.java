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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.example.pedido_api.domain.Pedido;
import com.example.pedido_api.service.PedidoSvc;
import com.example.pedido_api.service.exception.NumeroPedidoObrigatorioException;
import com.example.pedido_api.service.exception.ClientePedidoObrigatorioException;

@RestController

@RequestMapping("/api/v1/pedido")
public class PedidoCtrl {

    private final PedidoSvc pedidoService;

    public PedidoCtrl(PedidoSvc pedidoService) {
        this.pedidoService = pedidoService;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public class PedidoResponse {
        private int numero;
        private String cliente;

        public PedidoResponse() {
            this.numero = 0;
            this.cliente = "";
        }

        public PedidoResponse(Pedido pedido) {
            this.numero = pedido.getNumero();
            this.cliente = pedido.getCliente();
        }

        public PedidoResponse(String mensagem) {
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
    public ResponseEntity<PedidoResponse> postPedido(@RequestBody Pedido pedido) {
        try {
            Pedido novoPedido = this.pedidoService.InserirPedido(pedido);

            return new ResponseEntity<PedidoResponse>(new PedidoResponse(novoPedido), HttpStatus.CREATED);
        } catch (NumeroPedidoObrigatorioException e) {
            return new ResponseEntity<PedidoResponse>(new PedidoResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (ClientePedidoObrigatorioException e) {
            return new ResponseEntity<PedidoResponse>(new PedidoResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{numero}")
    public ResponseEntity<PedidoResponse> getPedido(@PathVariable("numero") Int numero) {
        Pedido pedido = this.pedidoService.RecuperarPedido(numero);

        if (pedido == null) {
            return new ResponseEntity<PedidoResponse>(new PedidoResponse("Numero inexistente"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<PedidoResponse>(new PedidoResponse(pedido), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Collection<PedidoResponse>> getListaPedidos() {
        Collection<Pedido> listaPedidos = this.pedidoService.RecuperarListaPedidos();
        Collection<PedidoResponse> listaPedidosResponse = new ArrayList<PedidoResponse>();

        for (Pedido pedido : listaPedidos) {
            listaPedidosResponse.add(new PedidoResponse(pedido));
        }

        return new ResponseEntity<Collection<PedidoResponse>>(listaPedidosResponse, HttpStatus.OK);
    }
}
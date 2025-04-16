package org.example.Models;

import org.example.Models.Enums.TipoPagamentoEnum;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private double valorTotal = 0;
    private List<Produto> carrinho = new ArrayList<>();
    private TipoPagamentoEnum metodoDePagamento;

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<Produto> getCarrinho() {
        return carrinho;
    }

    public TipoPagamentoEnum getMetodoDePagamento() {
        return metodoDePagamento;
    }

    public void setMetodoDePagamento(TipoPagamentoEnum metodoDePagamento) {
        this.metodoDePagamento = metodoDePagamento;
    }
}

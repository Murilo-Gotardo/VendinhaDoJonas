package org.example.Services;

import org.example.Models.Enums.TipoPagamentoEnum;
import org.example.Models.Pedido;
import org.example.Models.Produto;

public class PedidoService {

    public void adicionarAoCarrinho(Produto produto, Pedido pedido){
        double novoValor = pedido.getValorTotal() + produto.getValor();

        pedido.getCarrinho().add(produto);
        pedido.setValorTotal(novoValor);
    }

    public boolean definirMetodoDePagamento(Pedido pedido, TipoPagamentoEnum pagamentoEnum) {
        if (pedido.getCarrinho().isEmpty()) {
            System.out.println("Não é possível definir método de pagamento com o carrinho vazio");

            return false;
        }

        pedido.setMetodoDePagamento(pagamentoEnum);

        return true;
    }
}

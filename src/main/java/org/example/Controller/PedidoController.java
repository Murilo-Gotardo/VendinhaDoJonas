package org.example.Controller;

import org.example.Models.Pedido;
import org.example.Models.Produto;


public class PedidoController {
    public void adicionarAoCarrinho(Produto produto, Pedido pedido){
        double novoValor = pedido.getValorTotal() + produto.getValor();

        pedido.getCarrinho().add(produto);
        pedido.setValorTotal(novoValor);
    }


}

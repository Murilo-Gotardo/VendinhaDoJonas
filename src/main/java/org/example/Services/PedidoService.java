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

    public boolean validarPossibilidadeDeParcelas(Pedido pedido) {
        if (pedido.getMetodoDePagamento() != TipoPagamentoEnum.CARTAO_CREDITO) {
            throw new IllegalArgumentException("Metodo de pagamento nao suportado para parcelas");
        }

        if (pedido.getValorTotal() < 0) {
            throw new IllegalArgumentException("Valor total inválido");
        }

        return pedido.getValorTotal() > 20;
    }

    public boolean definirParcelas(Pedido pedido, int parcelas) {
        boolean ehPossivelTerParcela = validarPossibilidadeDeParcelas(pedido);

        if (ehPossivelTerParcela) {
            pedido.setParcelas(parcelas);

            return true;
        }

        return false;
    }

    public boolean finalizarPedido(Pedido pedido) {
        if (pedido.getCarrinho() == null || pedido.getCarrinho().isEmpty()) {
            System.out.println("Carrinho está vazio. Adicione produtos antes de finalizar o pedido.");
            return false;
        }

        if (pedido.getMetodoDePagamento() == null) {
            System.out.println("Defina um método de pagamento antes de finalizar o pedido.");
            return false;
        }

        System.out.println("Finalizando pedido...");
        System.out.println("Itens no carrinho:");
        for (Produto produto : pedido.getCarrinho()) {
            System.out.println("- " + produto.getNome() + " R$" + produto.getValor());
        }

        System.out.println("Valor total: R$" + pedido.getValorTotal());
        System.out.println("Método de pagamento: " + pedido.getMetodoDePagamento());

        if (pedido.getMetodoDePagamento() == TipoPagamentoEnum.CARTAO_CREDITO && pedido.getParcelas() > 1) {
            System.out.println("Parcelado em " + pedido.getParcelas() + "x de R$" + (pedido.getValorTotal() / pedido.getParcelas()));
        }

        System.out.println("Pedido finalizado com sucesso!");

        return true;
    }
}

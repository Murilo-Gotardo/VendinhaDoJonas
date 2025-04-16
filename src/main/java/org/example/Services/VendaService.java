package org.example.Services;

import org.example.Models.Enums.TipoPagamentoEnum;
import org.example.Models.Pedido;
import org.example.Models.Venda;

public class VendaService {

    public boolean validarPossibilidadeDeParcelas(Pedido pedido) {
        if (pedido.getMetodoDePagamento() != TipoPagamentoEnum.CARTAO_CREDITO) {
            throw new IllegalArgumentException("Metodo de pagamento nao suportado para parcelas");
        }

        if (pedido.getValorTotal() < 0) {
            throw new IllegalArgumentException("Valor total inválido");
        }

        return pedido.getValorTotal() > 20;
    }

    public boolean definirParcelas(Pedido pedido, Venda venda, int parcelas) {
        boolean ehPossivelTerParcela = validarPossibilidadeDeParcelas(pedido);

        if (ehPossivelTerParcela) {
            venda.setParcelas(parcelas);

            return true;
        }

        return false;
    }
}

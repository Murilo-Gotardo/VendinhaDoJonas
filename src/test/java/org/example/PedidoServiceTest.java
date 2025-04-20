package org.example;

import org.example.Models.Enums.TipoPagamentoEnum;
import org.example.Models.Pedido;
import org.example.Models.Produto;
import org.example.Services.PedidoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PedidoServiceTest {

    private Pedido pedido;
    private PedidoService pedidoService;

    @BeforeEach
    void setUp() {
        pedido = new Pedido();
        pedidoService = new PedidoService();
    }

    // --- validarPossibilidadeDeParcelas() ---

    @Test
    void deveRetornarTrueSeValorTotalMaiorQue20ECartaoCredito() {
        pedido.setMetodoDePagamento(TipoPagamentoEnum.CARTAO_CREDITO);
        pedidoService.adicionarAoCarrinho(new Produto("Produto", 25), pedido);

        boolean resultado = pedidoService.validarPossibilidadeDeParcelas(pedido);

        assertTrue(resultado);
    }

    @Test
    void deveRetornarFalseSeValorTotalMenorIgualA20() {
        pedido.setMetodoDePagamento(TipoPagamentoEnum.CARTAO_CREDITO);
        pedidoService.adicionarAoCarrinho(new Produto("Produto", 15), pedido);

        boolean resultado = pedidoService.validarPossibilidadeDeParcelas(pedido);

        assertFalse(resultado);
    }

    @Test
    void deveLancarExcecaoSeMetodoPagamentoNaoForCartaoCredito() {
        pedido.setMetodoDePagamento(TipoPagamentoEnum.DINHEIRO);
        pedidoService.adicionarAoCarrinho(new Produto("Produto", 30), pedido);

        assertThrows(IllegalArgumentException.class, () -> pedidoService.validarPossibilidadeDeParcelas(pedido));
    }

    @Test
    void deveLancarExcecaoSeValorTotalForNegativo() {
        pedido.setMetodoDePagamento(TipoPagamentoEnum.CARTAO_CREDITO);
        pedidoService.adicionarAoCarrinho(new Produto("Produto", -10), pedido);

        assertThrows(IllegalArgumentException.class, () -> pedidoService.validarPossibilidadeDeParcelas(pedido));
    }

    // --- definirParcelas() ---

    @Test
    void deveDefinirParcelasQuandoPossivel() {
        pedido.setMetodoDePagamento(TipoPagamentoEnum.CARTAO_CREDITO);
        pedidoService.adicionarAoCarrinho(new Produto("Produto", 100), pedido);

        boolean sucesso = pedidoService.definirParcelas(pedido, 3);

        assertTrue(sucesso);
        assertEquals(3, pedido.getParcelas());
    }

    @Test
    void naoDeveDefinirParcelasQuandoNaoForPossivel() {
        pedido.setMetodoDePagamento(TipoPagamentoEnum.CARTAO_CREDITO);
        pedidoService.adicionarAoCarrinho(new Produto("Produto", 15), pedido);

        boolean sucesso = pedidoService.definirParcelas(pedido, 2);

        assertFalse(sucesso);
        assertEquals(0, pedido.getParcelas());
    }

    // --- finalizarPedido() ---

    @Test
    void deveFinalizarPedidoComSucesso() {
        pedido.setMetodoDePagamento(TipoPagamentoEnum.DINHEIRO);
        pedidoService.adicionarAoCarrinho(new Produto("Produto", 50), pedido);

        boolean sucesso = pedidoService.finalizarPedido(pedido);

        assertTrue(sucesso);
    }

    @Test
    void naoDeveFinalizarPedidoSemMetodoPagamento() {
        pedidoService.adicionarAoCarrinho(new Produto("Produto", 50), pedido);

        boolean sucesso = pedidoService.finalizarPedido(pedido);

        assertFalse(sucesso);
    }

    @Test
    void naoDeveFinalizarPedidoComCarrinhoVazio() {
        pedido.setMetodoDePagamento(TipoPagamentoEnum.PIX);

        boolean sucesso = pedidoService.finalizarPedido(pedido);

        assertFalse(sucesso);
    }
}

package org.example;

import org.example.Services.ProdutoService;
import org.example.Models.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoServiceTest {

    private ProdutoService controller;
    private List<Produto> produtos;

    @BeforeEach
    void setUp() {
        this.produtos = new ArrayList<>();
        this.controller = new ProdutoService(produtos);
    }

    @Test
    void testRetornaNullSeAdicionaProdutoSemNome() {
        controller.adicionaProduto("", 20);
        assertNull(controller.getProdutos());
    }

    @Test
    void testRetornaNullSeAdicionaProdutoSemValor() {
        controller.adicionaProduto("pao", 0);
        assertNull(controller.getProdutos());
    }

    @Test
    void testRetornaNullSeAdicionaProdutoComValorNegativo() {
        controller.adicionaProduto("pao", -1);
        assertNull(controller.getProdutos());
    }

    @Test
    void testRetornaNovoProduto(){
        controller.adicionaProduto("pao", 20);

        assertNotNull(controller.getProdutos());
        assertEquals("pao", controller.getProdutos().get(0).getNome());
        assertEquals(20,    controller.getProdutos().get(0).getValor());
    }


    @Test
    void testeNaoRemoveNadaSeNomeNaoExiste() {
        controller.adicionaProduto("pao", 20);

        controller.removeProduto("livro");

        assertEquals(1, controller.getProdutos().size());
        assertEquals("pao", controller.getProdutos().get(0).getNome());
    }

    @Test
    void testeNaoRemoveNadaSeListaVazia(){

    }

    @Test
    void testeRemoveProdutoDeNomeCorrespondente(){

    }


    @Test
    void alteraProduto() {
    }
}
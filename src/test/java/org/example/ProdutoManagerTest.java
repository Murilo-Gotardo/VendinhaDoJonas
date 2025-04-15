package org.example;

import org.example.Controller.ProdutoController;
import org.example.Models.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoControllerTest {

    private ProdutoController controller;

    @BeforeEach
    void setUp() {
        List<Produto> produtos = new ArrayList<>();
        this.controller = new ProdutoController(produtos);
    }

    @Test
    void testeRetornaNullSeAdicionaProdutoSemNome() {
        controller.adicionaProduto("", 20);
        assertEquals(0, controller.getProdutos().size());
    }

    @Test
    void testeRetornaNullSeAdicionaProdutoSemValor() {
        controller.adicionaProduto("pao", 0);
        assertEquals(0, controller.getProdutos().size());
    }

    @Test
    void testeRetornaNullSeAdicionaProdutoComValorNegativo() {
        controller.adicionaProduto("pao", -1);
        assertEquals(0, controller.getProdutos().size());
    }

    @Test
    void testeRetornaNovoProduto(){
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
        controller.removeProduto("livro");
        assertEquals(0, controller.getProdutos().size());
    }

    @Test
    void testeRemoveProdutoDeNomeCorrespondente(){
        controller.adicionaProduto("pao", 20);
        controller.adicionaProduto("livro", 60.5);

        controller.removeProduto("pao");

        assertEquals(1, controller.getProdutos().size());
        assertEquals("livro", controller.getProdutos().get(0).getNome());
    }


    @Test
    void testeNaoAlteraProdutoSeListaVazia() {
        controller.alteraProduto(1, "caderno", 12.89);
        assertEquals(0, controller.getProdutos().size());
    }

    @Test
    void testeNaoAlteraProdutoSeIdNaoExiste() {
        controller.adicionaProduto("pao", 20);
        controller.alteraProduto(3, "caderno", 12.89);

        assertEquals("pao", controller.getProdutos().get(0).getNome());
    }

    @Test
    void testeNaoAlteraProdutoSeNomeVazio() {
        controller.adicionaProduto("pao", 20);
        controller.alteraProduto(0, "", 12.89);

        assertEquals("pao", controller.getProdutos().get(0).getNome());
    }

    @Test
    void testeNaoAlteraProdutoSeValorNegativo(){
        controller.adicionaProduto("pao", 20);
        controller.alteraProduto(0, "pao", -1);

        assertEquals(20, controller.getProdutos().get(0).getValor());
    }

    @Test
    void testeAlteraProdutoComIdCorrespondente() {
        controller.adicionaProduto("pao", 20);
        controller.alteraProduto(0, "caderno", 60.5);

        assertEquals("caderno", controller.getProdutos().get(0).getNome());
        assertEquals(60.5, controller.getProdutos().get(0).getValor());
    }
}
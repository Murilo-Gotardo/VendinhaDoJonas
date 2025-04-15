package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoManagerTest {

    private ProdutoManager manager;
    private List<Produto> produtos;

    @BeforeEach
    void setUp() {
        this.produtos = new ArrayList<>();
        this.manager = new ProdutoManager(produtos);
    }

    @Test
    void testRetornaNullSeAdicionaProdutoSemNome() {
        manager.adicionaProduto("", 20);
        assertNull(manager.getProdutos());
    }

    @Test
    void testRetornaNullSeAdicionaProdutoSemValor() {
        manager.adicionaProduto("pao", 0);
        assertNull(manager.getProdutos());
    }

    @Test
    void testRetornaNullSeAdicionaProdutoComValorNegativo() {
        manager.adicionaProduto("pao", -1);
        assertNull(manager.getProdutos());
    }

    @Test
    void testRetornaNovoProduto(){
        manager.adicionaProduto("pao", 20);

        assertNotNull(manager.getProdutos());
        assertEquals("pao", manager.getProdutos().getFirst().getNome());
        assertEquals(20,    manager.getProdutos().getFirst().getValor());
    }


    @Test
    void testeNaoRemoveNadaSeNomeNaoExiste() {
        manager.adicionaProduto("pao", 20);

        manager.removeProduto("livro");

        assertEquals(1, manager.getProdutos().size());
        assertEquals("pao", manager.getProdutos().getFirst().getNome());
    }

    @Test
    void testeNaoRemoveNadaSeListaVazia(){
        manager.removeProduto("livro");
        assertEquals(0, manager.getProdutos().size());
    }

    @Test
    void testeRemoveProdutoDeNomeCorrespondente(){
        manager.adicionaProduto("pao", 20);
        manager.adicionaProduto("livro", 60.5);

        manager.removeProduto("pao");

        assertEquals(1, manager.getProdutos().size());
        assertEquals("livro", manager.getProdutos().getFirst().getNome());
    }


    @Test
    void alteraProduto() {

    }
}
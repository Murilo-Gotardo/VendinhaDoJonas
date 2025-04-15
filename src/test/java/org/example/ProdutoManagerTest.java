package org.example;

import org.example.Services.ProdutoService;
import org.example.Models.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoServiceTest {

    private ProdutoService produtoService;

    @BeforeEach
    void setUp() {
        List<Produto> produtos = new ArrayList<>();
        this.produtoService = new ProdutoService(produtos);
    }

    @Test
    void testeRetornaNullSeAdicionaProdutoSemNome() {
        produtoService.adicionaProduto("", 20);
        assertEquals(0, produtoService.getProdutos().size());
    }

    @Test
    void testeRetornaNullSeAdicionaProdutoSemValor() {
        produtoService.adicionaProduto("pao", 0);
        assertEquals(0, produtoService.getProdutos().size());
    }

    @Test
    void testeRetornaNullSeAdicionaProdutoComValorNegativo() {
        produtoService.adicionaProduto("pao", -1);
        assertEquals(0, produtoService.getProdutos().size());
    }

    @Test
    void testeRetornaNovoProduto(){
        produtoService.adicionaProduto("pao", 20);

        assertNotNull(produtoService.getProdutos());
        assertEquals("pao", produtoService.getProdutos().getFirst().getNome());
        assertEquals(20,    produtoService.getProdutos().getFirst().getValor());
    }


    @Test
    void testeNaoRemoveNadaSeNomeNaoExiste() {
        produtoService.adicionaProduto("pao", 20);

        produtoService.removeProduto("livro");

        assertEquals(1, produtoService.getProdutos().size());
        assertEquals("pao", produtoService.getProdutos().getFirst().getNome());
    }

    @Test
    void testeNaoRemoveNadaSeListaVazia(){
        produtoService.removeProduto("livro");
        assertEquals(0, produtoService.getProdutos().size());
    }

    @Test
    void testeRemoveProdutoDeNomeCorrespondente(){
        produtoService.adicionaProduto("pao", 20);
        produtoService.adicionaProduto("livro", 60.5);

        produtoService.removeProduto("pao");

        assertEquals(1, produtoService.getProdutos().size());
        assertEquals("livro", produtoService.getProdutos().getFirst().getNome());
    }


    @Test
    void testeNaoAlteraProdutoSeListaVazia() {
        produtoService.alteraProduto(1, "caderno", 12.89);
        assertEquals(0, produtoService.getProdutos().size());
    }

    @Test
    void testeNaoAlteraProdutoSeIdNaoExiste() {
        produtoService.adicionaProduto("pao", 20);
        produtoService.alteraProduto(3, "caderno", 12.89);

        assertEquals("pao", produtoService.getProdutos().getFirst().getNome());
    }

    @Test
    void testeNaoAlteraProdutoSeNomeVazio() {
        produtoService.adicionaProduto("pao", 20);
        produtoService.alteraProduto(0, "", 12.89);

        assertEquals("pao", produtoService.getProdutos().getFirst().getNome());
    }

    @Test
    void testeNaoAlteraProdutoSeValorNegativo(){
        produtoService.adicionaProduto("pao", 20);
        produtoService.alteraProduto(0, "pao", -1);

        assertEquals(20, produtoService.getProdutos().getFirst().getValor());
    }

    @Test
    void testeAlteraProdutoComIdCorrespondente() {
        produtoService.adicionaProduto("pao", 20);
        produtoService.alteraProduto(0, "caderno", 60.5);

        assertEquals("caderno", produtoService.getProdutos().getFirst().getNome());
        assertEquals(60.5, produtoService.getProdutos().getFirst().getValor());
    }
}
package org.example.Services;

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
    void testeRetornaFalseSeAdicionaProdutoSemNome() {
        assertFalse(produtoService.adicionaProduto("", 20));
        assertEquals(0, produtoService.getProdutos().size());
    }

    @Test
    void testeRetornaFalseSeAdicionaProdutoSemValor() {
        assertFalse(produtoService.adicionaProduto("pao", 0));
        assertEquals(0, produtoService.getProdutos().size());
    }

    @Test
    void testeRetornaFalseSeAdicionaProdutoComValorNegativo() {
        assertFalse(produtoService.adicionaProduto("pao", -1));
        assertEquals(0, produtoService.getProdutos().size());
    }

    @Test
    void testeRetornaTrueAoAddNovoProduto(){
        assertTrue(produtoService.adicionaProduto("pao", 20));
        assertNotNull(produtoService.getProdutos());
    }


    @Test
    void testeRetornaNullSeIdNaoExiste() {
        produtoService.adicionaProduto("pao", 20);

        assertNull(produtoService.removeProduto(3));
        assertEquals(1, produtoService.getProdutos().size());
        assertEquals("pao", produtoService.getProdutos().getFirst().getNome());
    }

    @Test
    void testRetornaNullSeListaVazia(){
        assertNull(produtoService.removeProduto(2));
        assertEquals(0, produtoService.getProdutos().size());
    }

    @Test
    void testeRetornaProdutoRemovido(){
        produtoService.adicionaProduto("pao", 20);
        produtoService.adicionaProduto("livro", 60.5);

        assertEquals(produtoService.getProdutos().getFirst(), produtoService.removeProduto(0));
    }


    @Test
    void testeRetornaFalseSeListaVazia() {
        assertFalse(produtoService.modificaProduto(1, "caderno", 12.89));
    }

    @Test
    void testeRetornaFalseSeIdNaoExiste() {
        produtoService.adicionaProduto("pao", 20);
        assertFalse(produtoService.modificaProduto(3, "caderno", 12.89));
    }

    @Test
    void testeRetornaFalseSeNomeVazio() {
        produtoService.adicionaProduto("pao", 20);
        assertFalse(produtoService.modificaProduto(0, "", 12.89));
    }

    @Test
    void testeRetonaFalseSeValorNegativo(){
        produtoService.adicionaProduto("pao", 20);
        assertFalse(produtoService.modificaProduto(0, "caderno", -1));
    }

    @Test
    void testeRetornaTrueAoModificarProduto() {
        produtoService.adicionaProduto("pao", 20);
        assertTrue(produtoService.modificaProduto(0, "caderno", 12.89));
    }
}
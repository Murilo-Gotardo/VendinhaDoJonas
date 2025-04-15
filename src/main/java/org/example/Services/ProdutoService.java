package org.example.Services;

import org.example.Models.Produto;

import java.util.List;

public class ProdutoService {

    private List<Produto> produtos;

    public ProdutoService(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void adicionaProduto(String nome, double valor) {
        if (!nome.equalsIgnoreCase("") && valor > 0) produtos.add(new Produto(nome, valor));
    }

    public void removeProduto(String nome) {
        if (!produtos.isEmpty()){
            produtos.removeIf(produto -> produto.getNome().equalsIgnoreCase(nome));
        }
    }

    public void alteraProduto(String nome, double valor) {

    }

}

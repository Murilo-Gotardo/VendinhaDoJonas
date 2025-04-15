package org.example.Controller;

import org.example.Models.Produto;

import java.util.List;

public class ProdutoController {

    private List<Produto> produtos;

    public ProdutoController(List<Produto> produtos) {
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

    public void alteraProduto(int id, String novoNome, double novoValor) {
        if (!produtos.isEmpty() && produtos.size() > id) {
            if (!novoNome.equalsIgnoreCase("") && novoValor > 0) {
                produtos.get(id).setNome(novoNome);
                produtos.get(id).setValor(novoValor);
            }
        }
    }
}

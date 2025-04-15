package org.example;

import java.util.List;

public class ProdutoManager {

    private List<Produto> produtos;

    public ProdutoManager(List<Produto> produtos) {
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

    public void alteraProduto(String nome, double novoValor, String novoNome) {
        if (!produtos.isEmpty()){
            if (!novoNome.equalsIgnoreCase("") && novoValor > 0) {
                for (Produto produto : produtos) {
                    if (produto.getNome().equalsIgnoreCase(nome)) {
                        produto.setNome(novoNome);
                        produto.setValor(novoValor);
                    }
                }
            }
        }
    }
}

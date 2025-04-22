package org.example.Services;

import org.example.Models.Produto;

import java.util.List;

public class ProdutoService {

    private List<Produto> produtosEstoque;

    public ProdutoService(List<Produto> produtos) {
        this.produtosEstoque = produtos;
    }

    public List<Produto> getProdutos() {
        return produtosEstoque;
    }

    public boolean adicionaProduto(String nome, double valor) {
        if (!nome.equalsIgnoreCase("") && valor > 0) {
            produtosEstoque.add(new Produto(nome, valor));
            return true;
        }
        return false;
    }

    public Produto removeProduto(int id) {
        if (!produtosEstoque.isEmpty() && id < produtosEstoque.size()) {
            return produtosEstoque.remove(id);
        }
        return null;
    }

    public boolean modificaProduto(int id, String novoNome, double novoValor) {
        if (!produtosEstoque.isEmpty() && produtosEstoque.size() > id) {
            if (!novoNome.equalsIgnoreCase("") && novoValor > 0) {
                produtosEstoque.get(id).setNome(novoNome);
                produtosEstoque.get(id).setValor(novoValor);
                return true;
            }
        }
        return false;
    }
}

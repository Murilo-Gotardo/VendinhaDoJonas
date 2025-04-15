package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Produto> produtos = new ArrayList<Produto>();
        ProdutoManager manager = new ProdutoManager(produtos);

        produtos.add(new Produto("pao", 20));
        produtos.add(new Produto("bolacha", 3));
        produtos.add(new Produto("queijo", 12.9));


    }
}
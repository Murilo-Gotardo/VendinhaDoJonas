package org.example;

import org.example.Models.Enums.TipoPagamentoEnum;
import org.example.Models.Pedido;
import org.example.Models.Produto;
import org.example.Services.PedidoService;
import org.example.Services.ProdutoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Produto> produtosEstoque = new ArrayList<>();
    private static final ProdutoService produtoService = new ProdutoService(produtosEstoque);
    private static final Pedido pedido = new Pedido();
    private static final PedidoService pedidoService = new PedidoService();

    public static void main(String[] args) {

        produtosEstoque.add(new Produto("pao", 20));
        produtosEstoque.add(new Produto("bolacha", 3));
        produtosEstoque.add(new Produto("queijo", 12.9));

        while (true) {
            exibirMenuPrincipal();

            int opcao = lerInt("Escolha uma opção:");

            switch (opcao) {
                case 1 -> menuEstoque();
                case 2 -> menuCarrinho();
                case 3 -> escolherMetodoPagamento();
                case 4 -> escolherParcelamento();
                case 5 -> finalizarPedido();
                case 0 -> {
                    System.out.println("Saindo...");
                    return;
                }
                default -> System.out.println("Opção inválida.");
            }

            System.out.println("-----------------------------------\n");
        }
    }

    // --- MENU PRINCIPAL ---
    private static void exibirMenuPrincipal() {
        System.out.println("======== MENU PRINCIPAL ========");
        System.out.println("1 - Menu de Estoque");
        System.out.println("2 - Menu do Carrinho");
        System.out.println("3 - Escolher método de pagamento");
        System.out.println("4 - Definir parcelas da compra");
        System.out.println("5 - Finalizar Pedido");
        System.out.println("0 - Sair");
        System.out.println("================================");
    }

    // --- SUBMENU ESTOQUE ---
    private static void menuEstoque() {
        while (true) {
            System.out.println("\n====== MENU ESTOQUE ======");
            System.out.println("1 - Adicionar Produto");
            System.out.println("2 - Listar Produtos");
            System.out.println("3 - Editar Produto");
            System.out.println("4 - Remover Produto");
            System.out.println("0 - Voltar");
            System.out.println("==========================");

            int opcao = lerInt("Escolha uma opção:");

            switch (opcao) {
                case 1 -> adicionarProdutoAoEstoque();
                case 2 -> listarEstoque();
                case 3 -> modificarProduto();
                case 4 -> removerProduto();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    // --- SUBMENU CARRINHO ---
    private static void menuCarrinho() {
        while (true) {
            System.out.println("\n====== MENU CARRINHO ======");
            System.out.println("1 - Adicionar Produto ao Carrinho");
            System.out.println("2 - Listar Produtos do Carrinho");
            System.out.println("0 - Voltar");
            System.out.println("===========================");

            int opcao = lerInt("Escolha uma opção:");

            switch (opcao) {
                case 1 -> adicionarProdutoAoCarrinho();
                case 2 -> listarCarrinho();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    // --- MÉTODOS DE AÇÃO ---
    private static void adicionarProdutoAoEstoque() {
        System.out.print("Nome do produto: ");
        String nome = scanner.nextLine();
        double preco = lerDouble();
        produtosEstoque.add(new Produto(nome, preco));
        System.out.println("Produto adicionado com sucesso!");
    }

    private static void listarEstoque() {
        System.out.println("Produtos no estoque:");
        for (int i = 0; i < produtosEstoque.size(); i++) {
            Produto produto = produtosEstoque.get(i);
            System.out.printf("%d - %s - R$%.2f%n", (i + 1), produto.getNome(), produto.getValor());
        }
    }

    private static void modificarProduto() {
        // TODO: Implementar modificarProduto
    }

    private static void removerProduto() {
        listarEstoque();
        int escolha = lerInt("Digite o número do produto para remover:");
        if (escolha < 1 || escolha > produtosEstoque.size()) {
            System.out.println("Produto inválido.");
            return;
        }

        Produto removido = produtosEstoque.remove(escolha - 1);
        System.out.println("Produto '" + removido.getNome() + "' removido com sucesso.");
    }

    private static void adicionarProdutoAoCarrinho() {
        listarEstoque();
        int escolha = lerInt("Escolha o número do produto para adicionar ao carrinho:");
        if (escolha >= 1 && escolha <= produtosEstoque.size()) {
            Produto produtoEscolhido = produtosEstoque.get(escolha - 1);
            pedidoService.adicionarAoCarrinho(produtoEscolhido, pedido);
            System.out.println("Produto adicionado ao carrinho.");
        } else {
            System.out.println("Opção inválida.");
        }
    }

    private static void listarCarrinho() {
        List<Produto> carrinho = pedido.getCarrinho();
        if (carrinho.isEmpty()) {
            System.out.println("Carrinho vazio.");
            return;
        }

        System.out.println("Produtos no carrinho:");
        for (int i = 0; i < carrinho.size(); i++) {
            Produto produto = carrinho.get(i);
            System.out.printf("%d - %s - R$%.2f%n", (i + 1), produto.getNome(), produto.getValor());
        }
    }

    private static void escolherMetodoPagamento() {
        TipoPagamentoEnum[] opcoes = TipoPagamentoEnum.values();

        System.out.println("Escolha o método de pagamento:");
        for (int i = 0; i < opcoes.length; i++) {
            System.out.println((i + 1) + " - " + opcoes[i]);
        }

        int escolha = lerInt("Digite o número correspondente:");

        if (escolha >= 1 && escolha <= opcoes.length) {
            TipoPagamentoEnum metodoEscolhido = opcoes[escolha - 1];
            boolean isSuccess = pedidoService.definirMetodoDePagamento(pedido, metodoEscolhido);

            if (isSuccess) {
                System.out.println("Método de pagamento definido como: " + metodoEscolhido);
            }
        } else {
            System.out.println("Opção inválida.");
        }
    }

    private static void escolherParcelamento() {
        // TODO: Implementar escolherParcelamento
    }

    private static void finalizarPedido() {
        // TODO: Implementar finalizarPedido
    }

    // --- MÉTODOS DE ENTRADA AUXILIARES ---
    private static int lerInt(String mensagem) {
        System.out.print(mensagem + " ");
        while (!scanner.hasNextInt()) {
            System.out.print("Digite um número inteiro válido: ");
            scanner.next();
        }

        int valor = Integer.parseInt(String.valueOf(scanner.nextInt()));
        scanner.nextLine();
        return valor;
    }

    private static double lerDouble() {
        System.out.print("Preço: " + " ");
        while (!scanner.hasNextDouble()) {
            System.out.print("Digite um número válido: ");
            scanner.next();
        }

        double valor = Double.parseDouble(String.valueOf(scanner.nextDouble()));
        scanner.nextLine();
        return valor;
    }
}

package main;

import modelo.Produto;
import utils.Utilitarios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Mercado {
    private static Scanner input = new Scanner(System.in);
    private static ArrayList<Produto> produtos;
    private static Map<Produto, Integer> carrinho;

    public static void main (String[] args){
        produtos = new ArrayList<>();
        carrinho = new HashMap<>();
        menu();
    }

    private static void menu(){
        System.out.println("*--------------------------------------------*");
        System.out.println("----------Welcome to Pedro Market-------------");
        System.out.println("----------------------------------------------");
        System.out.println("**Selecione uma operação que deseja realizar**");
        System.out.println("----------------------------------------------");
        System.out.println("|   Opção 1 - Cadastrar Produto  |");
        System.out.println("|   Opção 2 - Listar Produto     |");
        System.out.println("|   Opção 3 - Comprar Produto    |");
        System.out.println("|   Opção 4 - Carrinho           |");
        System.out.println("|   Opção 5 - Sair               |");
        System.out.println("*--------------------------------------------*");

        int option = input.nextInt();

        switch (option) {
            case 1:
                cadastrarProduto();
                break;
            case 2:
                listarProduto();
                break;
            case 3:
                comprarProduto();
                break;
            case 4:
                verCarrinho();
                break;
            case 5:
                System.out.println("Volte Sempre !!!");
                System.exit(0);
            default:
                System.out.println("Opção Inválida!");
                menu();
                break;
        }
    }

    private static void cadastrarProduto(){
        System.out.println("Nome do produto: ");
        String nome = input.next();

        System.out.println("Preço do produto: ");
        Double preco = input.nextDouble();

        Produto produto = new Produto(nome,preco);
        produtos.add(produto);

        System.out.println(produto.getNome() + " cadastrado com sucesso!!");
        menu();
    }
    private static void listarProduto(){
        if (produtos.size() > 0){
            System.out.println("Lista de produtos: \n");

            for (Produto p : produtos) {
                System.out.println(p);
            }
        }else {
            System.out.println("Nenhum produto cadastrado! ");
        }
        menu();
    }

    private static void comprarProduto(){
       if (produtos.size() > 0){
           System.out.println("Código do produto: \n");

           System.out.println("--------------------Produtos Disponíveis--------------------");
           for (Produto p : produtos) {
               System.out.println(p + "\n");
           }
           int id = Integer.parseInt(input.next());
           boolean isPresent = false;

           for (Produto p : produtos){
               if (p.getId() == id){
                   int qtd = 0;
                   try {
                       qtd = carrinho.get(p);
                       carrinho.put(p, qtd +1);
                   }catch (NullPointerException e) {
                       carrinho.put(p, 1);
                   }
                   System.out.println(p.getNome() + " adicionado ao carrinho.");
                   isPresent = true;

                   if (isPresent){
                       System.out.println("Deseja adicionar mais algum produto ao carrinho? ");
                       System.out.println("Digite 1 para sim, ou 0 para finalizar a compra. \n");
                       int option = Integer.parseInt(input.next());

                       if (option == 1){
                           comprarProduto();
                       }else {
                           finalizarCompra();
                       }
                   }
               }else {
                   System.out.println("Produto não encontrado.");
                   menu();
               }
           }
       }else{
           System.out.println("Não existem produtos cadastrados!");
           menu();
       }
    }

    private static void verCarrinho(){
        System.out.println("-----Produtos no seu carrinho!-----");
        if (carrinho.size() > 0){
            for (Produto p : carrinho.keySet()){
                System.out.println("Produto: "+ p + " \nQuantidade: " + carrinho.get(p));
            }
        }else {
            System.out.println("Carrinho vazio!");
        }
        menu();
    }

    private static void finalizarCompra(){
        Double valorDaCompra = 0.0;
        System.out.println("Seus produtos!");

        for (Produto p : carrinho.keySet()){
            int qtd = carrinho.get(p);
            valorDaCompra += p.getPreco() * qtd;
            System.out.println(p);
            System.out.println("Quantidade: " + qtd);
            System.out.println("--------------------");
        }
        System.out.println("O valor da sua compra é: "+ Utilitarios.doubleToString(valorDaCompra));
        carrinho.clear();
        System.out.println("Volte sempre e compre mais vezes!");
    }
}

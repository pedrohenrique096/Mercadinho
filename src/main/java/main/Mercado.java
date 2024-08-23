package main;

import modelo.Produto;

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
}

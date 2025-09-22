package util;

import modelo.Produto;
import javax.swing.*;
import java.util.ArrayList;


public class Pedido {
    //Enum que define os possiveis status de um pedido
    public enum Status {
        EM_ANDAMENTO, ENVIADO, ENTREGUE
    }

    private final ArrayList<Produto> produtos;  // lista de produtos do pedido
    private Status status;

    public Pedido() {
        this.produtos = new ArrayList<>(); //inicializa a lista vazia de produtos
        this.status = Status.EM_ANDAMENTO; //inicia o pedido em andamento
    }

    //adiciona um produto ao pedido
    public void adicionarProduto(Produto p) {
        produtos.add(p);
    }


    //calcula o valor do pedido
    public double calcularTotal() {
        double total = 0.0;
        for (Produto p : produtos) {
            total += p.getPreco();
        }
        return total;
    }

    //cria uma string com a lista do pedido e o total
    public String listarProdutos() {
        StringBuilder itensPedidos = new StringBuilder("ITENS DO PEDIDO:\n");
        for (Produto p : produtos) {
            itensPedidos.append(p.exibirDetalhes()).append("\n");
        }
        itensPedidos.append("TOTAL: R$").append(calcularTotal());
        return itensPedidos.toString();
    }

    //retorna a lista de produtos
    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    //metodo get e set
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }

    //lista produtos + status
    @Override
    public String toString() {
        return listarProdutos() + "\nSTATUS: " + status;
    }
}

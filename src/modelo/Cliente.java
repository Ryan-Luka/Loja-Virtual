package modelo;

import util.Pedido; //importa a classe Pedido do package util
import java.util.ArrayList;

public class Cliente {
    //atributos da classe Cliente
    private final String nome;
    private final String cpf;
    private final String email;
    private final int idCliente;

    //contador para gerar os ids
    public static int contador = 1;

    //lista de pedidos feitos pelo cliente
    private final ArrayList<Pedido> pedidosClientes;

    //construtor
    public Cliente(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.idCliente = contador++; //id auto-incremento

        //inicializa a lista de pedidos do cliente
        this.pedidosClientes = new ArrayList<>();

    }

    //metodos get e set
    public String getNome() {
        return nome;
    }


    public String getCpf() {
        return cpf;
    }


    public int getIdCliente() {
        return idCliente;
    }

    //sobrescreve o metodo toString para exibir dados do cliente
    public String toString() {
        return "NOME: " + nome +
                ", CPF: " + cpf +
                ", E-MAIL: " + email;
    }

    public String exibirDetalhesCliente() {
        return "ID: " + idCliente +
                ", NOME: " + nome +
                ", CPF: " + cpf +
                ", E-MAIL: " + email;
    }

    //adiciona um pedido a lista de pedidos do cliente
    public void adicionarPedido(Pedido pedido) {
        pedidosClientes.add(pedido);
    }

    //retorna a lista de pedidos
    public ArrayList<Pedido> getPedidos() {
        return pedidosClientes;
    }
}

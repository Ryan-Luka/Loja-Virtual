package modelo;

//classe abstrata base para todos os produtos
public abstract class Produto {
    //atributos
    private final String nome;
    private final double preco;
    private int estoque;
    private final int id;

    //contador para gerar os ids
    public static int contador = 1;

    //construtor
    public Produto(String nome, double preco, int estoque) {
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
        this.id = contador++; //id auto-incremento
    }

    //metodos get e set
    public double getPreco() {
        return preco;
    }


    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    //metodo abstrato para definir o tipo do produto (digital ou fisico)
    public abstract String tipo();

    public final int getId(){
        return id;
    }

    //sobrescreve o metodo toString para exibir dados do produto
    @Override
    public String toString() {
        return "ID: " + id +
                ", Nome: " + nome +
                ", PREÇO: R$" + preco +
                ", ESTOQUE: " + estoque;
    }

    public String exibirDetalhes() {
        return "ID: " + id +
                ", NOME: " + nome +
                ", PREÇO: R$" + preco;
    }


}

package modelo;

//classe para produto fisico, subclasse de Produto (herda de Produto)
public class ProdutoFisico extends Produto{
    //atributo exclusivo do produto fisico
    private final double peso;

    //construtor
    public ProdutoFisico(String nome, double preco, int estoque, double peso) {
        super(nome, preco, estoque); //chama o construtor da classe pai (Produto)
        this.peso = peso;
    }

    //medotos get e set
    public double getPeso() {
        return peso;
    }

    //implementação do metodo abstrato da classe Produto
    @Override
    public String tipo() {
        return "FÍSICO";
    }

    //representação do produto fisico
    @Override
    public String toString() {
        return super.toString() +
                ", TIPO: " + tipo() +
                " , PESO: " + getPeso() + " kg";
        //chama o toString da classe Produto e acrescenta o tipo e o peso
    }

    @Override
    public String exibirDetalhes() {
        return super.exibirDetalhes() +
                ", TIPO: " + tipo() +
                " , PESO: " + getPeso() + " kg";
        //chama o metodo exibirDetalhes da classe produto e acrescenta o tipo e o peso
    }
}
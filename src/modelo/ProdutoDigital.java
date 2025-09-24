package modelo;

//classe para produto digital, subclasse de Produto (herda de Produto)
public class ProdutoDigital extends Produto {
    //atributo exclusivo do produto digital
    private final String formato;

    //construtor
    public ProdutoDigital(String nome, double preco, int estoque, String formato) {
        super(nome, preco, estoque);
        this.formato = formato;
    }

    //metodos get e set
    public String getFormato() {
        return formato;
    }

    //implementação do metodo abstrato da classe Produto
    @Override
    public String tipo() {
        return "DIGITAL";
    }

    //representação do produto digital
    @Override
    public String toString() {
        return super.toString() +
                ", TIPO: " + tipo() +
                ", FORMATO: " + getFormato();
        //chama o toString da classe Produto e acrescenta o tipo e formato
    }

    @Override
    public String exibirDetalhes() {
        return super.exibirDetalhes() +
                ", TIPO: " + tipo() +
                ", FORMATO: " + getFormato();
        //chama o metodo exibirDetalhes da classe produto e acrescenta o tipo
    }
}
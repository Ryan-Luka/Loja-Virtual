package aplicacao;

import modelo.Cliente;
import modelo.ProdutoDigital;
import modelo.ProdutoFisico;
import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        ProdutoFisico p1 = new ProdutoFisico("Notebook Dell Inspiron", 3500.00, 1, 2.5);
        ProdutoFisico p2 = new ProdutoFisico("Mouse Gamer Redragon", 120.00, 50, 0.2);
        ProdutoFisico p3 = new ProdutoFisico("Teclado Mecânico HyperX", 450.00, 30, 1.0);
        ProdutoFisico p4 = new ProdutoFisico("Cadeira Gamer DXRacer", 1200.00, 5, 15.0);

        ProdutoDigital d1 = new ProdutoDigital("E-book: Programação em Java", 39.90, 100, "PDF");
        ProdutoDigital d2 = new ProdutoDigital("Curso Online: Spring Boot", 199.90, 80, "Plataforma Online");
        ProdutoDigital d3 = new ProdutoDigital("Licença Antivírus 1 Ano", 89.90, 200, "Código Digital");

        Cliente c1 = new Cliente("Lucas Silva", "123.456.789-00", "lucas.silva@email.com");
        Cliente c2 = new Cliente("Mariana Oliveira", "987.654.321-00", "mariana.oli@email.com");
        Cliente c3 = new Cliente("Carlos Souza", "111.222.333-44", "carlos.souza@email.com");
        Cliente c4 = new Cliente("Ana Pereira", "555.666.777-88", "ana.pereira@email.com");


        Loja loja = new Loja();

        loja.cadastrarProdutoFisico(p1);
        loja.cadastrarProdutoFisico(p2);
        loja.cadastrarProdutoFisico(p3);
        loja.cadastrarProdutoFisico(p4);

        loja.cadastrarProdutoDigital(d1);
        loja.cadastrarProdutoDigital(d2);
        loja.cadastrarProdutoDigital(d3);

        loja.cadastrarCliente(c1);
        loja.cadastrarCliente(c2);
        loja.cadastrarCliente(c3);
        loja.cadastrarCliente(c4);

        boolean executando = true;
        while(executando){
            int opcao = Integer.parseInt(JOptionPane.showInputDialog(null,
                    """
                    LOJA VIRTUAL - MENU
                    1 - CADASTRAR PRODUTO
                    2 - CADASTRAR CLIENTE
                    3 - CRIAR PEDIDO
                    4 - ALTERAR STATUS DO PEDIDO
                    5 - RELATÓRIO: PEDIDOS POR CLIENTE
                    6 - CHECAR ESTOQUE BAIXO
                    0 - SAIR
                    ESCOLHA UMA OPÇÃO:
                    """,
                    "MENU",
                    JOptionPane.QUESTION_MESSAGE));

            switch (opcao) {
                case 1:
                    String nome = JOptionPane.showInputDialog(null, "Informe o nome do produto:");
                    double preco = Double.parseDouble(JOptionPane.showInputDialog(null, "Informe o valor do produto:"));
                    int quantidade = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe a quantidade:"));
                    opcao = Integer.parseInt(JOptionPane.showInputDialog(null,
                        """
                                QUAL O TIPO DE PRODUTO?
                                1 - FÍSICO
                                2 - DIGITAL
                                0 - CANCELAR CADASTRO
                                """,
                                "CADASTRO DE PRODUTO",
                                JOptionPane.QUESTION_MESSAGE));
                    if (opcao == 1) {
                        double peso = Double.parseDouble(JOptionPane.showInputDialog(null, "Informe o peso:"));
                        loja.cadastrarProdutoFisico(new ProdutoFisico(nome, preco, quantidade, peso));
                    }else if (opcao == 2) {
                        String formato = JOptionPane.showInputDialog(null, "Informe o formato do produto:");
                        loja.cadastrarProdutoDigital(new ProdutoDigital(nome, preco, quantidade, formato));
                    }else if (opcao == 0) {
                        break;
                    }
                    break;

                case 2:
                    String nomeCliente = JOptionPane.showInputDialog(null, "Informe o nome do Cliente:");
                    String cpfCliente = JOptionPane.showInputDialog(null, "Informe o cpf do cliente:");
                    String emailCliente = JOptionPane.showInputDialog(null, "Informe o email do cliente:");
                    loja.cadastrarCliente(new Cliente(nomeCliente, cpfCliente, emailCliente));
                    break;

                case 3:
                    loja.criarPedido();
                    break;

                case 4:
                    loja.alterarStatusPedido();
                    break;

                case 5:
                    loja.gerarRelatorioPedidosPorCliente();
                    break;

                case 6:
                    loja.checarEstoqueBaixo(2);
                    break;

                case 0:
                    executando = false;

                default:
                    System.out.println();
            }
        }

    }
}

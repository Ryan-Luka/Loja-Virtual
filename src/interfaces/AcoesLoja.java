package interfaces;

import modelo.Produto;
import modelo.Cliente;
import modelo.ProdutoDigital;
import modelo.ProdutoFisico;

//interface com os metodos/ações a serem realizadas, a classe loja deve implementar todos os metodos obrigatoriamente
public interface AcoesLoja {
    //cadastra um produto fisico na loja
    void cadastrarProdutoFisico(ProdutoFisico p);
    //cadastra um produto digital na loja
    void cadastrarProdutoDigital(ProdutoDigital p);
    //cadastra um novo cliente
    void cadastrarCliente(Cliente c);
    //busca um produto a partir do seu id
    Produto buscarProdutoPorId(int id);
    //busca um cliente a partir do seu id
    Cliente buscarClientePorId(int id);
    //cria um pedido para um cliente associando produtos a ele
    void criarPedido();
    //gera um relatório de todos os pedidos agrupados por cliente
    void gerarRelatorioPedidosPorCliente();
    //permite alterar o status de um pedido existente
    void alterarStatusPedido();
    //verifica se existem produtos com estoque abaixo de um limite minimo
    void checarEstoqueBaixo(int  limiteMinimo);
}


package aplicacao;
import java.util.ArrayList;
import interfaces.AcoesLoja;
import modelo.Cliente;
import modelo.ProdutoFisico;
import modelo.ProdutoDigital;
import modelo.Produto;
import util.Pedido;
import javax.swing.*;

//a classe Loja implementa a interface AcoesLoja
public class Loja implements AcoesLoja {

    //listas que armazenam os produtos (separados por tipo), os clientes e os pedidos da loja
    private final ArrayList<ProdutoDigital> produtosDigital;
    private final ArrayList<ProdutoFisico> produtosFisico;
    private final ArrayList<Cliente> clientes;
    private final ArrayList<Pedido> pedidos;

    //construtor inicializa as listas
    public Loja() {
        this.produtosDigital = new ArrayList<>();
        this.produtosFisico = new ArrayList<>();
        this.pedidos = new ArrayList<>();
        this.clientes = new ArrayList<>();
    }

    //metodos de cadastro
    @Override
    public void cadastrarProdutoFisico(ProdutoFisico p) {
        produtosFisico.add(p);
    }

    @Override
    public void cadastrarProdutoDigital(ProdutoDigital p) {
        produtosDigital.add(p);
    }

    @Override
    public void cadastrarCliente(Cliente c) {
        for(Cliente cliente : clientes) {
            //verifica se o cpf digitado já foi cadastrado antes
            if(cliente.getCpf().equals(c.getCpf())) {
                JOptionPane.showMessageDialog(null, "CLIENTE JÁ POSSUI CADASTRO NA LOJA! (CPF JÁ CADASTRADO)");
                return;
            }
        }
        clientes.add(c);
    }

    @Override
    public Produto buscarProdutoPorId(int id) {
        //percorre a lista de produtos fisicos procurando pelo id
        for (ProdutoFisico pf : produtosFisico) {
            if (pf.getId() == id) {
                return pf; //se encontrar, retorna o produto fisico
            }
        }
        //se não encontrar, procura entre os digitais
        for (ProdutoDigital pd : produtosDigital) {
            if (pd.getId() == id) {
                return pd; //se encontrar, retorna o produto digital
            }
        }
        //se não encontrar em nenhuma lista, retorna null (produto não existe)
        return null;
    }

    @Override
    public Cliente buscarClientePorId(int id) {
        //percorre a lista de clientes buscando pelo id
        for (Cliente c : clientes) {
            if (c.getIdCliente() == id) {
                return c; //retorna o cliente encontrado
            }
        }
        return null; //retorna null se não encontrar
    }

    //metodo para criar pedido
    @Override
    public void criarPedido() {
        //se não houver clientes cadastrados, impede a criação do pedido
        if (clientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "NENHUM CLIENTE CADASTRADO!");
            return;
        }

        //monta uma string listando os clientes com os seus detalhes para o usuário escolher
        StringBuilder listaClientes = new StringBuilder("SELECIONE UM CLIENTE:\n");
        for (Cliente c : clientes) {
            listaClientes.append(c.exibirDetalhesCliente()).append("\n");
            //exibirDetalhesCliente() da classe cliente já traz if, nome, cpf e email
        }

        //pede ao usuario o id do cliente ou 0 para cancelar
        int idCliente = Integer.parseInt(JOptionPane.showInputDialog(null,
                listaClientes + "\nDIGITE O CÓDIGO ID DO CLIENTE (OU '0' PARA CANCELAR):",
                "PEDIDO",
                JOptionPane.QUESTION_MESSAGE));

        //se o usuario escolher cancelar (0), encerra o metodo
        if (idCliente == 0) {
            JOptionPane.showMessageDialog(null, "PEDIDO CANCELADO!");
            return;
        }

        //busca o cliente selecionado pelo id com o metodo buscarClientePorId()
        Cliente clienteEscolhido = buscarClientePorId(idCliente); //se encontrar, a variavel clienteEscolhido guardará a referencia ao objeto cliente

        //se não encontrou o cliente, avisa e termina
        if (clienteEscolhido == null) {
            JOptionPane.showMessageDialog(null, "CLIENTE NÃO ENCONTRADO!");
            return;
        }

        //cria um pedido vazio que receberá vários itens
        Pedido pedido = new Pedido();

        //loop para adicionar varios produtos ao pedido
        while (true) {

            // Monta a lista de produtos disponíveis
            StringBuilder produtosDisponiveis = new StringBuilder("PRODUTOS COM ESTOQUE DISPONÍVEL:\n");

            //adiciona a string os produtos fisicos com estoque > 0
            for (ProdutoFisico produtoFisico : produtosFisico) {
                if (produtoFisico.getEstoque() > 0) {
                    produtosDisponiveis.append(produtoFisico).append("\n");
                    //usando toString() nativo que contem todas as informações do produto
                }
            }

            //adiciona a string os produtos digital com estoque > o
            for (ProdutoDigital produtoDigital : produtosDigital) {
                if (produtoDigital.getEstoque() > 0) {
                    produtosDisponiveis.append(produtoDigital).append("\n");
                    //usando toString() que contem todas as informações do produto
                }
            }

            //pergunta ao usuario qual id de produto deseja adionar (ou 0 para finalizar o pedido)
            int idProduto = Integer.parseInt(JOptionPane.showInputDialog(null,
                    produtosDisponiveis + "\nDIGITE O CÓDIGO ID DO PRODUTO (OU '0' PARA FINALIZAR O PEDIDO):",
                    "SELEÇÃO DE PRODUTOS",
                    JOptionPane.QUESTION_MESSAGE));

            //se o usuario digitou 0 encerra o loop e finaliza a montagem do pedido
            if (idProduto == 0) {
                break;
            }

            //busca o produto pelo id informado
            Produto escolhido = buscarProdutoPorId(idProduto); //se encontrar, a variavel escolhido guardará a referencia ao objeto Produto

            if (escolhido != null) {
                //verifica o estoque caso o cliente digite o código de um produto sem estoque
                if (escolhido.getEstoque() < 1) {
                    JOptionPane.showMessageDialog(null, "PRODUTO SEM ESTOQUE!");
                }else{
                    pedido.adicionarProduto(escolhido); //adiciona o produto encontrado ao pedido
                    JOptionPane.showMessageDialog(null,
                            "PRODUTO ADICIONADO AO PEDIDO:\n" + escolhido.exibirDetalhes());
                    escolhido.setEstoque(escolhido.getEstoque() - 1); //atualiza o estoque do produto escolhido
                }
            } else {
                //avisa se o produto não foi encontrado
                JOptionPane.showMessageDialog(null, "PRODUTO NÃO ENCONTRADO!");
            }
        }

        //após montar o pedido, se houver pelo menos um produto, registra o pedido
        if(!pedido.getProdutos().isEmpty()){
            clienteEscolhido.adicionarPedido(pedido); //adiciona o pedido ao histórico do cliente
            pedidos.add(pedido); //registra o pedido na lista global da loja

            JOptionPane.showMessageDialog(null, "PEDIDO FINALIZADO PARA O CLIENTE: " + clienteEscolhido.getNome() + "\n\n" + pedido.listarProdutos());
        }else{
            JOptionPane.showMessageDialog(null, "NENHUM PRODUTO FOI ADICIONADO AO PEDIDO!");
        }
    }

    //relatório de pedidos por cliente
    @Override
    public void gerarRelatorioPedidosPorCliente() {
        //se não há clientes, não gera relatório
        if (clientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "NENHUM CLIENTE CADASTRADO!");
            return;
        }

        //constroi o texto do relatorio percorrendo todos os clientes
        StringBuilder relatorio = new StringBuilder("RELATÓRIO DE PEDIDOS POR CLIENTE:\n\n");

        for (Cliente c : clientes) {
            //usando toString() que contem as informações do cliente
            relatorio.append(c.toString()).append("\n");

            if (c.getPedidos().isEmpty()) {
                relatorio.append(" - NENHUM PEDIDO REALIZADO.\n\n");
            } else {
                //lista todos os pedidos do cliente e adiciona no relatorio
                relatorio.append(c).append("\n");
                for (Pedido p : c.getPedidos()) {
                    relatorio.append(" - PEDIDO: \n")
                            .append(p.listarProdutos())
                            .append("\nSTATUS: ")
                            .append(p.getStatus())
                            .append("\n");
                }
            }
            relatorio.append("------------------------------------------------\n");
        }

        //exibe o relatório final
        JOptionPane.showMessageDialog(null, relatorio.toString());
    }

    @Override
    public void alterarStatusPedido() {
        //verifica se tem pedidos
        if (pedidos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "NÃO HÁ PEDIDOS EM ABERTO!");
            return;
        }

        //monta uma lista com todos os pedidos para o usuario escolher qual alterar
        StringBuilder listaPedidos = new StringBuilder("SELECIONE O PEDIDO PARA ALTERAR O STATUS:\n");

        for (int i = 0; i < pedidos.size(); i++) {
            listaPedidos.append("PEDIDO NÚMERO ")
                    .append(i + 1).append("°\n")
                    .append(pedidos.get(i).toString()) //toString() traz detalhes do pedido + status
                    .append("\n\n");
        }

        //pergunta ao usuario qual pedido deseja alterar
        int opcao = Integer.parseInt(JOptionPane.showInputDialog(null,
                listaPedidos + "DIGITE O NÚMERO DO PEDIDO:",
                "ALTERAR STATUS", JOptionPane.QUESTION_MESSAGE));

        //validação do indice escolhido
        if (opcao < 1 || opcao > pedidos.size()) {
            JOptionPane.showMessageDialog(null, "PEDIDO INVÁLIDO!");
            return;
        }

        //recupera o pedido selecionado ajustando o indice
        Pedido pedidoSelecionado = pedidos.get(opcao - 1);

        // Mostrar opções de status
        Pedido.Status[] status = Pedido.Status.values();
        StringBuilder opcoesStatus = new StringBuilder("ESCOLHA O NOVO STATUS:\n");
        for (int i = 0; i < status.length; i++) {
            opcoesStatus.append(i + 1).append(" - ").append(status[i]).append("\n");
        }

        //pergunta qual status o usuario deseja aplicar
        int novoStatus = Integer.parseInt(JOptionPane.showInputDialog(null,
                opcoesStatus.toString(),
                "STATUS", JOptionPane.QUESTION_MESSAGE));

        //validação de escolha
        if (novoStatus < 1 || novoStatus > status.length) {
            JOptionPane.showMessageDialog(null, "OPÇÃO INVÁLIDA!");
            return;
        }

        //atualiza o status do pedido com o escolhido
        pedidoSelecionado.setStatus(status[novoStatus - 1]);
        JOptionPane.showMessageDialog(null,
                "STATUS DO PEDIDO ATUALIZADO PARA: " + pedidoSelecionado.getStatus());

        if (pedidoSelecionado.getStatus() == Pedido.Status.ENTREGUE){
            pedidos.remove(pedidoSelecionado);
            JOptionPane.showMessageDialog(null, "O PEDIDO FOI ENTREGUE E REMOVIDO DA LISTA DE PEDIDOS EM ABERTO.");
        }
    }

    //verifica estoque baixo
    @Override
    public void checarEstoqueBaixo(int limiteMinimo) {
        //monta o texto inicial do alerta
        StringBuilder alerta = new StringBuilder("PRODUTOS COM ESTOQUE BAIXO (<= " + limiteMinimo + "):\n");

        //procura produtos físicos com estoque menor ou igual ao limite
        for (ProdutoFisico pf : produtosFisico) {
            if (pf.getEstoque() <= limiteMinimo) {
                alerta.append(pf).append("\n"); //usando toString() para mostrar todas as informações do produto
            }
        }

        //procura produtos digitais com estoque menor ou igual ao limite
        for (ProdutoDigital pd : produtosDigital) {
            if (pd.getEstoque() <= limiteMinimo) {
                alerta.append(pd).append("\n");
            }
        }

        //avisa se não tiver produtos com estoque baixo
        if (alerta.toString().equals("PRODUTOS COM ESTOQUE BAIXO (<= " + limiteMinimo + "):\n")) {
            JOptionPane.showMessageDialog(null, "NENHUM PRODUTO COM ESTOQUE BAIXO!");
        } else {
            int idProduto = Integer.parseInt(JOptionPane.showInputDialog(null,
                    alerta + "\nDIGITE O ID DO PRODUTO QUE DESEJA REPOR ESTOQUE (OU '0' PARA VOLTAR AO MENU):",
                    "ALERTA DE ESTOQUE", JOptionPane.WARNING_MESSAGE));

            if(idProduto == 0){
                return;
            }

            //busca o produto pelo id informado
            Produto escolhido = buscarProdutoPorId(idProduto); //se encontrar, a variavel escolhido guardará a referencia ao objeto Produto

            if(escolhido == null){
                JOptionPane.showMessageDialog(null, "PRODUTO NÃO ENCONTRADO!");
                return;
            }

            int quantidadeRepor = Integer.parseInt(JOptionPane.showInputDialog(null,
                    "INFORME A QUANTIDADE PARA REPOR NO ESTOQUE DO PRODUTO: \n" + escolhido.exibirDetalhes() + ", ESTOQUE: " + escolhido.getEstoque(),
                    "REPOSIÇÃO DE ESTOQUE", JOptionPane.QUESTION_MESSAGE));

            if (quantidadeRepor <= 0) {
                JOptionPane.showMessageDialog(null, "QUANTIDADE INVÁLIDA!");
                return;
            }
            escolhido.setEstoque(escolhido.getEstoque() + quantidadeRepor);
            JOptionPane.showMessageDialog(null,
                    "ESTOQUE ATUALIZADO COM SUCESSO!\n" + escolhido.exibirDetalhes() + ", ESTOQUE: " + escolhido.getEstoque());


        }
    }



}



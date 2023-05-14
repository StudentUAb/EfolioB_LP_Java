import org.jpl7.Query;
import org.jpl7.Term;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

//Classe que representa o sistema bancário
public class SistemaBancario {
    private ArrayList<Cliente> clientes;

    // Construtor, atributos e outros métodos existentes
    public SistemaBancario() {
        PrologConnection prologConnection = new PrologConnection();
        clientes = prologConnection.getClientes();
    }

    // Método para obter os clientes de determinada cidade
    public void getClientesPorCidade(String cidade) {
        String consulta = String.format("clientes_cidade('%s', NumCliente, Nome)", cidade);
        Query query = new Query(consulta);

        while (query.hasMoreSolutions()) {
            Map<String, Term> solucao = query.nextSolution();
            int numeroCliente = solucao.get("NumCliente").intValue();
            String nome = solucao.get("Nome").toString();
            System.out.println("Número de Cliente: " + numeroCliente + ", Nome: " + nome);
        }
    }

    // Método para imprimir a lista de clientes
    public void imprimirListaDeClientes() {
        System.out.println("Lista de Clientes:");
        System.out.println("-----------------------------------------------------");
        for (Cliente cliente : clientes) {
            System.out.println(cliente.toString());
        }
        System.out.println("-----------------------------------------------------");
    }

    // Método para obter os clientes elegíveis a crédito
    public void obterClientesElegiveisCredito() {
        PrologConnection prologConnection = new PrologConnection();
        ArrayList<Cliente> clientesElegiveis = prologConnection.getClientesElegiveisCredito();

        System.out.println("Clientes Elegíveis a Crédito:");
        System.out.println("-----------------------------------------------------");
        for (Cliente cliente : clientesElegiveis) {
            System.out.println(cliente.toString());
        }
        System.out.println("-----------------------------------------------------");
    }

    // Método para selecionar um cliente pelo número
    public Cliente selecionarCliente(int numeroCliente) {
            for (Cliente cliente : clientes) {
                if (cliente.getNumeroCliente() == numeroCliente) {
                    return cliente;
                }
            }
            return null;
    }

    // Método para exibir o menu de opções
    public void menu() {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\nMenu de opções:");
            System.out.println("1. Mostrar lista de clientes");
            System.out.println("2. Ver clientes de determinada cidade");
            System.out.println("3. Ver clientes elegíveis a crédito");
            System.out.println("4. Acessar operações de cliente pelo número"); // Nova opção
            System.out.println("5. Sair");
            System.out.print("Digite a opção desejada: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    imprimirListaDeClientes();
                    break;
                case 2:
                    scanner.nextLine(); // Limpar o buffer do Scanner
                    System.out.print("Digite a cidade: ");
                    String cidade = scanner.nextLine();
                    getClientesPorCidade(cidade);
                    break;
                case 3:
                    obterClientesElegiveisCredito();
                    break;
                    case 4:
                    System.out.print("Digite o número do cliente: ");
                    int numeroCliente = scanner.nextInt();
                    Cliente clienteSelecionado = selecionarCliente(numeroCliente);

                    if (clienteSelecionado != null) {
                        clienteSelecionado.menuCliente();
                    } else {
                        System.out.println("Cliente não encontrado.");
                    }
                    break;
                case 5:
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 5);
        scanner.close();
    }
    
}

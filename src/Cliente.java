import java.util.Scanner;
import java.util.ArrayList;

//Classe que representa o cliente
public class Cliente {
    private int numeroCliente;
    private String nome;
    private String agencia;
    private String cidade;
    private String dataAbertura;

    // Construtor
    public Cliente(int numeroCliente, String nome, String agencia, String cidade, String dataAbertura) {
        this.numeroCliente = numeroCliente;
        this.nome = nome;
        this.agencia = agencia;
        this.cidade = cidade;
        this.dataAbertura = dataAbertura;
    }

        // Método para imprimir o saldo real do cliente
        public void imprimirSaldoReal() {
            PrologConnection prologConnection = new PrologConnection();
            int saldoReal = prologConnection.getSaldoReal(this.numeroCliente);
            System.out.println("Saldo real do cliente " + this.nome + ": " + saldoReal);
        }

        // Método para imprimir o menu do cliente
        public void menuCliente() {
            Scanner scanner = new Scanner(System.in);
            int opcao;
    
            do {
                System.out.println("\nMenu do Cliente:");
                System.out.println("1. Ver saldo real");
                System.out.println("2. Ver balanço de crédito");
                System.out.println("3. Ver movimentos");
                System.out.println("4. Voltar ao menu anterior");
                System.out.print("Digite a opção desejada: ");
                opcao = scanner.nextInt();
        
                switch (opcao) {
                    case 1:
                        imprimirSaldoReal();
                        break;
                    case 2:
                        imprimirBalancoCredito();
                        break;
                    case 3:
                        imprimirMovimentos();
                        break;
                    case 4:
                        System.out.println("Voltando ao menu anterior...");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } while (opcao != 4);
      
        }

        //Método para imprimir o balanço de crédito do cliente
        public void imprimirBalancoCredito() {
            PrologConnection prologConnection = new PrologConnection();
            int balancoCredito = prologConnection.getBalancoCredito(this.numeroCliente);
            System.out.println("Balanço de crédito do cliente " + this.nome + ": " + balancoCredito);
        }

        //Método para imprimir os movimentos do cliente
        public void imprimirMovimentos() {
            PrologConnection prologConnection = new PrologConnection();
            ArrayList<String[]> movimentos = prologConnection.getMovimentosCliente(this.numeroCliente);
        
            System.out.println("Movimentos do cliente " + this.nome + ":");
            System.out.println("-----------------------------------------------------");
            for (String[] movimento : movimentos) {
                System.out.println("Valor: " + movimento[0] + ", Data: " + movimento[1]);
            }
            System.out.println("-----------------------------------------------------");
        }
        

    // Getters e setters
    public int getNumeroCliente() {
        return numeroCliente;
    }

    public void setNumeroCliente(int numeroCliente) {
        this.numeroCliente = numeroCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(String dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    // Método toString
    @Override
    public String toString() {
        return "Cliente{" +
                "numeroCliente=" + numeroCliente +
                ", nome='" + nome + '\'' +
                ", agencia='" + agencia + '\'' +
                ", cidade='" + cidade + '\'' +
                ", dataAbertura='" + dataAbertura + '\'' +
                '}';
    }
    
}




   

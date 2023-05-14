import org.jpl7.Query;
import org.jpl7.Term;
import java.util.ArrayList;
import java.util.Map;

public class PrologConnection {
    public PrologConnection() {
        Query q1 = new Query("consult", new Term[] {new org.jpl7.Atom("src/banco.pl")});
        q1.hasSolution();
    }

    public ArrayList<Cliente> getClientesElegiveisCredito() {
        String consulta = "clientes_elegiveis_credito(NumCliente, Nome, Agencia, Cidade, DataAbertura)";
        Query query = new Query(consulta);
        ArrayList<Cliente> clientes = new ArrayList<>();

        while (query.hasMoreSolutions()) {
            Map<String, Term> solucao = query.nextSolution();
            int numeroCliente = solucao.get("NumCliente").intValue();
            String nome = solucao.get("Nome").toString();
            String agencia = solucao.get("Agencia").toString();
            String cidade = solucao.get("Cidade").toString();
            String dataAbertura = solucao.get("DataAbertura").toString();

            Cliente cliente = new Cliente(numeroCliente, nome, agencia, cidade, dataAbertura);
            clientes.add(cliente);
        }

        return clientes;
    }

    public int getSaldoReal(int numeroCliente) {
        String consulta = String.format("saldo_real(%d, SaldoReal)", numeroCliente);
        Query query = new Query(consulta);
        Map<String, Term> solucao = query.oneSolution();
        return solucao.get("SaldoReal").intValue();
    }

    public int getBalancoCredito(int numeroCliente) {
        String consulta = String.format("credito(%d, BalancoCredito)", numeroCliente);
        Query query = new Query(consulta);
    
        if (query.hasSolution()) {
            Map<String, Term> solucao = query.oneSolution();
            return solucao.get("BalancoCredito").intValue();
        } else {
            System.out.println("Não foi possível obter o balanço de crédito para o cliente com número " + numeroCliente);
            return -1;
        }
    }
    
    
    public ArrayList<String[]> getMovimentosCliente(int numeroCliente) {
        String consulta = String.format("movimentos_cliente(%d, Valor, Data)", numeroCliente);
        Query query = new Query(consulta);
        ArrayList<String[]> movimentos = new ArrayList<>();
    
        while (query.hasMoreSolutions()) {
            Map<String, Term> solucao = query.nextSolution();
            String valor = solucao.get("Valor").toString();
            String data = solucao.get("Data").toString();
    
            movimentos.add(new String[]{valor, data});
        }
    
        return movimentos;
    }
    

    public ArrayList<Cliente> getClientes() {
        String consulta = "todos_clientes(NumCliente, Nome, Agencia, Cidade, DataAbertura)";
        Query query = new Query(consulta);
        ArrayList<Cliente> clientes = new ArrayList<>();

        while (query.hasMoreSolutions()) {
            Map<String, Term> solucao = query.nextSolution();
            int numeroCliente = solucao.get("NumCliente").intValue();
            String nome = solucao.get("Nome").toString();
            String agencia = solucao.get("Agencia").toString();
            String cidade = solucao.get("Cidade").toString();
            String dataAbertura = solucao.get("DataAbertura").toString();

            Cliente cliente = new Cliente(numeroCliente, nome, agencia, cidade, dataAbertura);
            clientes.add(cliente);
        }

        return clientes;
    }
}

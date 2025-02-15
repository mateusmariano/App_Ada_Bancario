package Agencia;

import Conta.Conta;
import Conta.ContaCorrente;
import Conta.ContaPoupanca;
import Utils.ContaUtil;
import Utils.JSONUtil;
import Utils.OpcaoInvalidaException;

import java.util.*;

public class AgenciaFuncionalidades {

    Scanner scanner;
    protected List<ContaCorrente> contasCorrentes;
    protected List<ContaPoupanca> contasPoupanca;
    protected ArrayList<Conta> contasTotais;

    public AgenciaFuncionalidades(
                            Scanner scanner, List<ContaCorrente> contasCorrentes,
                            List<ContaPoupanca> contasPoupanca,
                            ArrayList<Conta> contasTotais) {
        this.scanner = scanner;
        this.contasCorrentes = contasCorrentes;
        this.contasPoupanca = contasPoupanca;
        this.contasTotais = contasTotais;
    }


   void criarConta() {
        ContaUtil.TipoConta tipoConta = getTipoConta();
       if (tipoConta == null) return;

       String numConta = gerarNumeroConta(tipoConta);
       Conta novaConta = criarNovaConta(tipoConta, numConta);
       contasTotais.add(novaConta);
       salvarContasJSON(numConta);

    }
    private ContaUtil.TipoConta getTipoConta() {
        System.out.println("Qual tipo de conta deseja criar? \n 1 - Conta Poupança \n 2 - Conta Corrente");
        int opcao = scanner.nextInt();

        try {
            switch (opcao) {
                case 1: return ContaUtil.TipoConta.POUPANCA;
                case 2: return ContaUtil.TipoConta.CORRENTE;
                default: throw new OpcaoInvalidaException();
            }
        } catch (OpcaoInvalidaException e) {
            System.err.println("Houve um problema no programa: " + e.getMessage());
            return null;
        }
    }
    private String gerarNumeroConta(ContaUtil.TipoConta tipoConta) {
        switch (tipoConta) {
            case POUPANCA:
                return ContaUtil.gerarContaNumero(contasPoupanca.size() + 1, "p");
            case CORRENTE:
                return ContaUtil.gerarContaNumero(contasCorrentes.size() + 1, "c");
            default:
                return "";
        }
    }
    private Conta criarNovaConta(ContaUtil.TipoConta tipoConta, String numConta) {
        if (tipoConta == ContaUtil.TipoConta.CORRENTE) {
            ContaCorrente novaConta = new ContaCorrente(numConta, 0.0);
            contasCorrentes.add(novaConta);
            System.out.println("Conta Corrente criada. Numero: " + novaConta.getNumero());
            return novaConta;
        } else {
            ContaPoupanca novaConta = new ContaPoupanca(numConta, 0.0);
            contasPoupanca.add(novaConta);
            System.out.println("Conta Poupanca criada. Numero: " + novaConta.getNumero());
            return novaConta;
        }
    }

    void listarContas() {
        if(!contasPoupanca.isEmpty()){
            System.out.println("---------CONTAS POUPANCAS:---------");
            contasPoupanca.forEach((cp) -> System.out.println(cp.toString()));
        } else {
            System.out.println("não há contas poupanças cadastradas");
        }
        if(!contasCorrentes.isEmpty()){
            System.out.println("---------CONTAS CORRENTES:---------");
            contasCorrentes.forEach((cc) -> System.out.println(cc.toString()));
        } else {
            System.out.println("não há contas correntes cadastradas");
        }
    }

    void sacar() {
        try {
            System.out.println("Digite o numero da conta:");
            String numConta = scanner.next();

            System.out.println("Qual o valor do saque?");
            double valor = scanner.nextDouble();

            ContaUtil.acharConta(numConta, contasTotais).sacar(valor);
            salvarContasJSON(numConta);

        } catch (InputMismatchException e) {
            scanner.next();
            System.err.println("Usuário não inseriu os dados corretamente");
        }  catch (Exception e) {
            System.err.println("Conta não encontrada");
        }
    }

     void depositar() {
         try {
             System.out.println("Digite o numero da conta:");
             String numConta = scanner.next();

             System.out.println("Qual o valor do depósito?");
             double valor = scanner.nextDouble();

             ContaUtil.acharConta(numConta, contasTotais).depositar(valor);
             salvarContasJSON(numConta);

         } catch (InputMismatchException e) {
             scanner.next();
             System.err.println("Usuário não inseriu os dados corretamente");
         } catch (Exception e) {
             System.err.println("Conta não encontrada");
         }
     }

    void consultar() {
        try {
            System.out.println("Digite o numero da conta:");
            String numConta = scanner.next();
            Conta conta = null;

            conta = ContaUtil.acharConta(numConta, contasTotais);
            if (conta == null) {
                System.err.println("Conta não encontrada");
            } else {
                System.out.println(conta);
            }

        } catch (InputMismatchException e) {
            System.err.println("Usuário não inseriu os dados corretamente");
        }
    }

    void salvarContasJSON (String numConta) {
        if( ContaUtil.isCorrente(numConta)){
            JSONUtil.SaveContasJson(  contasCorrentes , JSONUtil.ARQUIVO_CORRENTE);
        } else {
            JSONUtil.SaveContasJson(  contasPoupanca , JSONUtil.ARQUIVO_POUPANCA);
        }
    }
}



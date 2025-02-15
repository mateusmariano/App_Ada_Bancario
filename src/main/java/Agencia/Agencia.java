package Agencia;

import Conta.Conta;
import Conta.ContaPoupanca;
import Conta.ContaCorrente;
import Utils.JSONUtil;
import Utils.OpcaoInvalidaException;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Agencia {

    static Scanner scanner = new Scanner(System.in);
    public List<ContaCorrente> contasCorrentes = List.of();
    public List<ContaPoupanca> contasPoupanca = List.of();
    public ArrayList<Conta> contasTotais = new ArrayList<Conta>();
    public AgenciaFuncionalidades agenciaFuncionalidades;

    public Agencia() {
        this.contasCorrentes = pegarContasCorrente();
        this.contasPoupanca = pegarContasPoupanca();
        this.contasTotais = pegarContasTotais();
        this.agenciaFuncionalidades = new AgenciaFuncionalidades( scanner, this.contasCorrentes,
                                                                  this.contasPoupanca,  this.contasTotais);
    }

    private static List<ContaCorrente> pegarContasCorrente() {
        return JSONUtil.PegarContasCorrenteJson();
    }

    private static List<ContaPoupanca> pegarContasPoupanca() {
        return JSONUtil.PegarContasPoupancaJson();
    }

    private ArrayList<Conta> pegarContasTotais() {
        ArrayList<Conta> todasAsContas = new ArrayList<>();
        todasAsContas.addAll(contasCorrentes);
        todasAsContas.addAll(contasPoupanca);
        return todasAsContas;
    }

    public void menu() {
        System.out.println("""
                 -----------AGENCIA BANCARIA -----------
                 1 - criar conta  
                 2 - sacar
                 3 - depositar
                 4 - consultar saldo
                 5 - listar contas
                 6 - sair
                """);

        try {
            int opcao = scanner.nextInt();
            tratarOpcao(opcao);
        } catch (InputMismatchException e) {
            System.err.println("Usuário não inseriu os dados corretamente");
            scanner.next();
            menu();
        }
    }

    private void tratarOpcao(int opcao) {
        try {
            switch (opcao) {
                case 1 -> agenciaFuncionalidades.criarConta();
                case 2 -> agenciaFuncionalidades.sacar();
                case 3 -> agenciaFuncionalidades.depositar();
                case 4 -> agenciaFuncionalidades.consultar();
                case 5 -> agenciaFuncionalidades.listarContas();
                case 6 -> sair();
                default -> throw new OpcaoInvalidaException();
            }
            menu();

        } catch (OpcaoInvalidaException e) {
            System.err.println("Houve um problema no programa: " + e.getMessage());
            menu();
        }
    }

    void sair() {
        System.out.println("Obrigado por usar o programa");
        System.exit(0);
    }
}

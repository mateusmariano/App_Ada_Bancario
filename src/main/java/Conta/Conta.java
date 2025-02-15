package Conta;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.DecimalFormat;

public abstract sealed class Conta permits ContaCorrente, ContaPoupanca {
    private String numero;
    private Double saldo = 0.0;

    @JsonCreator
    public Conta(
            @JsonProperty("numero") String numero,
            @JsonProperty("saldo") double saldo) {
        this.numero = numero;
        this.saldo = saldo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public void sacar(double valor) {
        double preSaque = this.getSaldo() - valor - (valor * 0.02);

        if(preSaque < 0 || preSaque == this.getSaldo()){
            System.out.println("Saque não realizado, informações incorretas");
        } else {
            this.setSaldo(preSaque);
            System.out.println("Saque realizado com sucesso. Saldo atual: " +
                                DecimalFormat.getCurrencyInstance().format(this.getSaldo()));
        }
    }

    public void depositar(double valor) {
        if(valor > 0){
            setSaldo(this.getSaldo() + valor);
            System.out.println("Deposito realizado com sucesso");
        } else {
            System.out.println("Não foi possível realizar o depósito");
        }
    }

    public String toString () {
        return STR."""
                Conta : \{this.getNumero()}
                Saldo : \{DecimalFormat.getCurrencyInstance().format(this.getSaldo())}
                """;
    }
}

package Conta;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.DecimalFormat;

public final class ContaPoupanca extends Conta{

    public double taxaRendimento;

    @JsonCreator
    public ContaPoupanca(
            @JsonProperty("numero") String numero,
            @JsonProperty("saldo") double saldo) {
        super(numero, saldo);
        this.taxaRendimento = 0.5;
    }

    public double getTaxaRendimento() {
        return taxaRendimento;
    }

    public void setTaxaRendimento(double taxaRendimento) {
        this.taxaRendimento = taxaRendimento;
    }

    @Override
    public void depositar(double valor) {
        if(valor > 0){
            this.setSaldo(this.getSaldo() + valor + (valor * 0.05));
            System.out.println("Deposito realizado com sucesso");
        } else {
            System.out.println("Não foi possível realizar o depósito");
        }
    }

    @Override
    public String toString() {
        return STR."""
                 Conta Poupanca nº : \{this.getNumero()}
                 Saldo : \{DecimalFormat.getCurrencyInstance().format(this.getSaldo())}
                """;
    }
}

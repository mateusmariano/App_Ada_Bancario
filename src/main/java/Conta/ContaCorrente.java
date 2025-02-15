package Conta;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.DecimalFormat;

public final class ContaCorrente extends Conta{

    @JsonCreator
    public ContaCorrente(
            @JsonProperty("numero") String numero,
            @JsonProperty("saldo") double saldo) {
        super(numero, saldo);
    }

    @Override
    public String toString() {
        return STR."""
                 Conta Corrente nº : \{this.getNumero()}
                 Saldo : \{DecimalFormat.getCurrencyInstance().format(this.getSaldo())}
                """;
    }
}

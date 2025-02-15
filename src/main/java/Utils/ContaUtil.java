package Utils;

import Conta.Conta;

import java.util.List;
import java.util.Random;

public class ContaUtil {

    public enum TipoConta {
        POUPANCA, CORRENTE
    }

    public static <T extends Conta> T acharConta(String numConta, List<T> contas) {
        for (T conta : contas) {
            if (conta.getNumero().equals(numConta)) {
                return conta;
            }
        }
        return null;
    }

    public static String gerarContaNumero(int num, String tipo) {
        Random random = new Random();
        StringBuilder numeroConta = new StringBuilder();
        int tamanhoBloco = 3;

        for (int j = 0; j < tamanhoBloco; j++) {
            int digito = random.nextInt(10);
            numeroConta.append(digito);
        }

        int digitoVerificador = random.nextBoolean() ? 10 : 20;
        numeroConta.append("-").append(digitoVerificador);

        return numeroConta.toString();
    }

    public static boolean isCorrente(String codConta){
        String substring = codConta.substring(codConta.indexOf("-"));
        return substring.contains("10");
    }
}

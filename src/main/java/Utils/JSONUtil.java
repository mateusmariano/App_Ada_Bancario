package Utils;

import Conta.ContaCorrente;
import Conta.ContaPoupanca;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class JSONUtil {

    public static final String ARQUIVO_CORRENTE = "contasBancarias_corrente.json";
    public static final String ARQUIVO_POUPANCA = "contasBancarias_poupanca.json";

    public static List<ContaCorrente> PegarContasCorrenteJson() {
        List<ContaCorrente> contasCorrentes = List.of();

        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        URL urlCorrentes = JsonParser.class.getClassLoader().getResource(ARQUIVO_CORRENTE);

        try {
            contasCorrentes = objectMapper.readValue( new File(urlCorrentes.getFile()),
                                                      new TypeReference<>() {} );

        } catch (IOException e) {
            System.out.println("não foi possivel pegar as contas correntes: erro: " );
            e.printStackTrace();
        }
        return contasCorrentes;
    }

    public static List<ContaPoupanca> PegarContasPoupancaJson() {
        List<ContaPoupanca> contasPoupanca = List.of();

        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        URL urlPoupanca = JsonParser.class.getClassLoader().getResource(ARQUIVO_POUPANCA);

        try {
            contasPoupanca = objectMapper.readValue(new File(urlPoupanca.getFile()),
                    new TypeReference<>() {} );

        } catch (IOException e) {
            System.out.println("não foi possivel pegar as contas poupancas: erro: ");
            e.printStackTrace();
        }
        return contasPoupanca;
    }

    public static <T> void SaveContasJson(List<T> contas, String nomeArquivo){
        ObjectMapper objectMapper = new ObjectMapper();
        String arrayToJson = null;
        URL url = JsonParser.class.getClassLoader().getResource(nomeArquivo);
        File file = new File(url.getFile());

        try {
            arrayToJson = objectMapper.writeValueAsString(contas);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file.getPath()))) {
                writer.write(arrayToJson);
                System.out.println("Contas atualizadas com sucesso");
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao processar operações", e);
        }
    }
}

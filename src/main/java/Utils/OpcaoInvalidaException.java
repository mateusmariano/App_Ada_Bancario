package Utils;

public class OpcaoInvalidaException extends Exception{
    public OpcaoInvalidaException(String message) {
        super(message);
    }
    public OpcaoInvalidaException() {
        super("Usuário não inseriu os dados corretamente");
    }
}

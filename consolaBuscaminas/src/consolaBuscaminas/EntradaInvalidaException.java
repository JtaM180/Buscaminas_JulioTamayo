package consolaBuscaminas;

public class EntradaInvalidaException extends Exception {
    private static final long serialVersionUID = 1L;

	public EntradaInvalidaException(String mensaje) {
        super(mensaje);
    }
}

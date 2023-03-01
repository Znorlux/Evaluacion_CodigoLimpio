public class CorreoInvalidoError extends Exception {
    public CorreoInvalidoError() {
        super();
    }
    public CorreoInvalidoError(String message) {
        super(message);
        System.out.println(message);
    }
}
public class InvitadoInexistenteError extends Exception {
   
    public InvitadoInexistenteError() {
        super();
    }
    //Sobrecarga de constructor - versión con mensaje
    public InvitadoInexistenteError(String message) {
        super(message);//Con este segundo constructor le podemos pasar un mensaje para que logre ser imprimido por consola
        System.out.println(message);
    }
}

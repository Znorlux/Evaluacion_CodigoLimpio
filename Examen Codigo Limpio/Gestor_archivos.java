import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
abstract class Gestor_archivos {
    //Definimos una lista que contendrá objetos de tipo Invitado, donde se almacenarán los invitados
    public List<Invitado> lista_invitados = new ArrayList<Invitado>();
    
    public abstract void leer_informacion(String ruta, String separador ) throws IOException;
    //Con los metodos abstractos logramos que las subclases implementen este metodo a su gusto
}


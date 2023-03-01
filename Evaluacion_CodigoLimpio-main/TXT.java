import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TXT extends Gestor_archivos {
    @Override
    public void leer_informacion(String ruta, String separador) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(ruta));
        String content = br.readLine(); 
        content = br.readLine();
        while (content != null) {
            String[] info = content.split(separador);
            String nombre = info[0];
            int id = Integer.parseInt(info[1]);
            String email = info[2];
            int edad = Integer.parseInt(info[3]);
            Invitado invitado = new Invitado(nombre, id, email, edad);
            this.lista_invitados.add(invitado);
            content = br.readLine();
        }
        br.close();
    }
}
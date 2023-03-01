import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSV extends Gestor_archivos {
    @Override
    //Sobreescribimos y definimos la funcion del metodo abstracto heredado desde Gestor_archivos
    public void leer_informacion(String path, String separador) throws IOException { //separador es "," para CSV o "\\s+" para TXT
    //BufferReader es StreamWritter, lo utilizamos para leer el archivo
    BufferedReader br = new BufferedReader(new FileReader(path));//Le damos como parametro un nuevo objeto de tipo FileReader que será el archivo
        String content = br.readLine(); //Saltamos la primera linea del CSV o TXT
        content = br.readLine();  //Guardamos el contenido de cada linea
        while (content != null) {//Mientras el contenido del csv o txt no sea vacio, hará el ciclo
            String[] info = content.split(separador);//Aqui se almacenará la informacion del invitado, ID, nombre, etc...
            String nombre = info[0];
            int id = Integer.parseInt(info[1]);//Debemos pasar el ID a entero y lo mismo para edad
            String email = info[2];
            int edad = Integer.parseInt(info[3]);
            Invitado invitado = new Invitado(nombre, id, email, edad);//Creamos un objeto de tipo invitado con los datos adquiridos
            this.lista_invitados.add(invitado);//Agregamos este objeto a la lista de invitados
            content = br.readLine();//Se lee la siguiente linea y se repite hasta que haya una linea vacia
        }
        br.close();
    }
}

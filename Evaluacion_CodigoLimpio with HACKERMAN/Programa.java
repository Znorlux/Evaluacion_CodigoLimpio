import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.Collections;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Programa {
    public String ruta;
    public Programa(String ruta_archivo){
        this.ruta = ruta_archivo;
    }

    public Hackerman hacker= new Hackerman();
    public Gestor_archivos file_manager;//Objeto de la clase abstracta Gestor de archivos para llamar a su metodo que es modificado por sus clases hijas
    public Verificador verificador = new Verificador();

    public String tipo_archivo(String ruta_archivo){
        if(ruta_archivo.contains(".txt")){
            System.out.println("Tipo de archivo utilizado actualmente: txt");
            String string = "\\s+";
            file_manager = new TXT();
            return string;//Retornamos el separador del archivo correspondiente, en este caso es un txt, por lo tanto es un espacio ("\\s+")
        }
        else if(ruta_archivo.contains(".csv")){
            System.out.println("Tipo de archivo utilizado actualmente: csv");
            String string = ",";
            file_manager = new CSV();
            return string;
        }
        else{
            return null;
        }
    }
    public Invitado buscar_invitado(int id){
        for(Invitado invitado : file_manager.lista_invitados){//Iteramos sobre la lista de invitados que está almacenada en la clase Gestor de archivos
            if(invitado.get_id() == id){
                return invitado;//Con este metodo logramos saber si el ID que ingresó el usuario, lo tiene algun objeto Invitado de la lista
            }
        }
        return null;
    }
    
    public void leer_archivo(String ruta, String separador){
        try {
            file_manager.leer_informacion(ruta, separador);
        } catch (IOException e) {
            // Manejar la excepción de entrada/salida aquí
            e.printStackTrace();
        }
    }
    public void reescribir_archivo() throws FileNotFoundException {
        try {
            FileWriter fileWriter = new FileWriter(this.ruta, false); // se sobrescribe el archivo existente
            PrintWriter printWriter = new PrintWriter(fileWriter);
            if(this.ruta.contains(".txt")){
                // Primero se imprime la línea "Nombre Id Email Edad"
                printWriter.println("Nombre Id Email Edad");
                for(Invitado invitados : file_manager.lista_invitados){
                    String nombre_inv = invitados.get_nombre();
                    int id_inv = invitados.get_id();
                    String email_inv = invitados.get_email();
                    int edad_inv = invitados.get_edad();
                    // Luego se imprimen los datos de los invitados
                    printWriter.println(nombre_inv + " " + id_inv + " " + email_inv + " " + edad_inv);
                }
            }
            else if(this.ruta.contains(".csv")){
                printWriter.println("Nombre,Id,Email,Edad");
                for(Invitado invitados : file_manager.lista_invitados){
                    String nombre_inv = invitados.get_nombre();
                    int id_inv = invitados.get_id();
                    String email_inv = invitados.get_email();
                    int edad_inv = invitados.get_edad();
                    printWriter.println(nombre_inv+","+  id_inv +","+  email_inv+","+ edad_inv);
                }
                
            }
            printWriter.close();
            preguntar_organizacion();
            
        } catch (IOException e) {
            System.err.println("La ruta del archivo no fue encontrada, por favor verificala");
            e.printStackTrace();
        }
    }
    public void preguntar_organizacion(){
        System.out.println("Ahora desea organizar la lista de invitados por nombre o edad? (Ingrese nombre)(Ingrese edad)\nSi no desea cambiarlo escriba n");
            Scanner input_final = new Scanner(System.in);
            String opcion_final = input_final.nextLine().toLowerCase();
            if(opcion_final.equals("nombre")){
                ordenar_nombres();
                int id_inv = MainClass.solicitar_id();
                verificar_invitado(id_inv);
            }else if(opcion_final.equals("edad")){
                ordenar_edades();
                int id_inv = MainClass.solicitar_id();
                verificar_invitado(id_inv);
            
            }else if(opcion_final.equals("n")){
                int id_inv = MainClass.solicitar_id();
                verificar_invitado(id_inv);
            }else{
                System.out.println("Ingresó un valor invalido, vuelva a intentarlo...");
                preguntar_organizacion();
            }
    }
    public void ordenar_nombres(){
        System.out.println("ordenando lista por nombre...");
       Collections.sort(file_manager.lista_invitados, new Comparator<Invitado>() {
            @Override
            public int compare(Invitado inv1, Invitado inv2) {
                String nombre1 = inv1.get_nombre().startsWith("?") ? inv1.get_nombre().substring(1) : inv1.get_nombre();
                String nombre2 = inv2.get_nombre().startsWith("?") ? inv2.get_nombre().substring(1) : inv2.get_nombre();
                return nombre1.compareTo(nombre2);
            }
        });
        for(Invitado invitados : file_manager.lista_invitados){
            String nombre_inv = invitados.get_nombre();
            int id_inv = invitados.get_id();
            String email_inv = invitados.get_email();
            int edad_inv = invitados.get_edad();
            // Luego se imprimen los datos de los invitados
            System.out.println(nombre_inv + " " + id_inv + " " + email_inv + " " + edad_inv);
        }
    }
    public void ordenar_edades(){
        System.out.println("Ordenando lista por edades");
        Collections.sort(file_manager.lista_invitados, new Comparator<Invitado>() {
            @Override
            public int compare(Invitado inv1, Invitado inv2) {
                return Integer.compare(inv1.get_edad(), inv2.get_edad());
            }
        });
        
        // Recorrer la lista de invitados e imprimir las edades
        for (Invitado invitado : file_manager.lista_invitados) {
            String nombre_inv = invitado.get_nombre();
            int id_inv = invitado.get_id();
            String email_inv = invitado.get_email();
            int edad_inv = invitado.get_edad();
            // Luego se imprimen los datos de los invitados
            System.out.println(nombre_inv + " " + id_inv + " " + email_inv + " " + edad_inv);
        }
    }
    
    
    public void verificar_invitado(int id){
        Invitado invitado = buscar_invitado(id);
        //Vamos verificando que todos los metodos de verificacion retornen True, a medida que uno no se vaya cumpliendo, se iran lanzando excepciones
        try{
            if (invitado != null){
                {
                    if(verificador.verificar_edad(invitado)){
                        if(verificador.verificar_correo(invitado)){
                            System.out.println("===========================================================");
                            System.out.println("Los datos de su registro son correctos, puede continuar");
                            System.out.println("===========================================================");
                            preguntar_organizacion();
                        }
                        else{
                            System.out.println("===========================================================");
                            throw new CorreoInvalidoError("El correo que registró es invalido, vuelva a intentarlo o llame a Hackerman");
                        }
                    }
                    else{
                        System.out.println("===========================================================");
                        throw new InvitadoMenordeEdad("El invitado buscado es menor de edad, vuelva a intentarlo o llame a Hackerman");
                    }
                }
            }
                else{
                    System.out.println("===========================================================");
                    throw new InvitadoInexistenteError("El invitado buscado no existe, vuelva a intentar otro ID o llame Hackerman");
                }                
            }
            catch(InvitadoInexistenteError e){
                int id_inv = MainClass.solicitar_id();
                verificar_invitado(id_inv);
            }
            catch(InvitadoMenordeEdad e){
                System.out.println("Desea llamar a Hackerman para cambiar su edad? (s/n)");
                Scanner sc = new Scanner(System.in);
                String opcion = sc.nextLine();
                if(opcion.equals("s") || opcion.equals("S")){
                    hacker.modificar_edad_inv(invitado.get_id(), file_manager);
                    System.out.println("Su edad ha sido modificada correctamente, a continuacion, vuelva a ingresar su ID");
                    preguntar_sobreescribir();
                    int id_inv = MainClass.solicitar_id();
                    verificar_invitado(id_inv);
                }
                else if(opcion.equals("n") || opcion.equals("N")){
                    int id_inv = MainClass.solicitar_id();
                    verificar_invitado(id_inv);
                }   
            }
            catch(CorreoInvalidoError e){
                System.out.println("Desea llamar a Hackerman para cambiar su email? (s/n)");
                Scanner sc = new Scanner(System.in);
                String opcion = sc.nextLine();
                if(opcion.equals("s") || opcion.equals("S")){
                    hacker.modificar_email_inv(invitado.get_id(), file_manager);
                    System.out.println("Su email ha sido modificada correctamente, a continuacion, vuelva a ingresar su ID");
                    preguntar_sobreescribir();
                    int id_inv = MainClass.solicitar_id();
                    verificar_invitado(id_inv);
                }
                else if(opcion.equals("n") || opcion.equals("N")){
                    int id_inv = MainClass.solicitar_id();
                    verificar_invitado(id_inv);
            }    
        }   
     }
    
    public void preguntar_sobreescribir(){
    Scanner pregunta = new Scanner(System.in);
    System.out.println("Desea sobreescribir e imprimir los cambios en archivo con los datos de invitados? (s/n)");
    String respuesta = pregunta.nextLine();
    if(respuesta.equals("S") || respuesta.equals("s")){
    try{
        reescribir_archivo();
    }catch(FileNotFoundException e){
        System.err.println("Error: Archivo no encontrado.");
        e.printStackTrace();
    }
      
    }
    else if(respuesta.equals("n") || respuesta.equals("N")){
      int id_inv = MainClass.solicitar_id();
      verificar_invitado(id_inv);
        } 
    }   
}


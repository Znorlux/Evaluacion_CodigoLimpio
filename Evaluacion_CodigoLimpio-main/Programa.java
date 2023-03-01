import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
public class Programa {
    
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
    public Invitado reescribir_archivo(){
        //String ruta = "Taller_herencia.txt";
        File file = new File("Taller_herencia.txt");

        System.out.println("Nombre ID Email Edad ");
        for(Invitado invitados : file_manager.lista_invitados){
            String nombre_inv = invitados.get_nombre();
            int id_inv = invitados.get_id();
            String email_inv = invitados.get_email();
            int edad_inv = invitados.get_edad();
            System.out.println(nombre_inv+" "+  id_inv +" "+  email_inv+" "+ edad_inv);
            try{
                PrintStream ps = new PrintStream(file);
                System.setOut(ps);
                System.out.println(nombre_inv+" "+  id_inv +" "+  email_inv+" "+ edad_inv);

                ps.close();
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }
        }
        return null;
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
                            int id_inv = MainClass.solicitar_id();
                            verificar_invitado(id_inv);
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
                System.out.println("Desea llamar a Hackerman para cambiar su edad? (s/n)");
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
    System.out.println("Desea sobreescribir e imprimir los cambios en la lista de invitados? (s/n)");
    String respuesta = pregunta.nextLine();
    if(respuesta.equals("S") || respuesta.equals("s")){
      reescribir_archivo();
    }
    else if(respuesta.equals("n") || respuesta.equals("N")){
      int id_inv = MainClass.solicitar_id();
      verificar_invitado(id_inv);
        } 
    }   
}


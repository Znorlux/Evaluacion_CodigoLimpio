import java.util.Scanner;
public class MainClass {
    
    public static void main(String[] args) {
    String ruta = "Taller_herencia.csv";
    //+dar_ruta(ruta);
    Programa Gestor_programa = new Programa(ruta);//Objeto de tipo programa para poder llamar a sus metodos
    String separador = Gestor_programa.tipo_archivo(ruta);//Detectamos al tipo del archivo(CSV o TXT)
    if (separador != null)
    {//Una vez sabemos que tipo de archivo es, sabremos que su separador es "," o "\\+s"
    Gestor_programa.leer_archivo(ruta, separador);//Leemos el archivo y luego su contenido en la clase correspondiente
    //Una vez hemos leido todos los datos del txt o csv, procedemos a preguntarle el ID al taquillero
      int id = solicitar_id();
      Gestor_programa.verificar_invitado(id);
    //} 
    //ID DE GMAIL VALIDO = 70322595
    //Ahora con ese ID, procedemos a hacer las verificaciones
    }
    else{
      //Si se definió una ruta del archivo que no es es txt o csv, habrá un error
      System.out.println("La extension del archivo no fue encontrada, revise la ruta y el archivo");
    }
  }
  public static int solicitar_id(){
    Scanner sc = new Scanner(System.in);
    //System.out.println("Bienvenido Taquillero al verificador de invitados");
    System.out.println("ingrese el ID del invitado que desea verificar");

    try {//Para capturar la excepcion de valores invalidos(Diferentes de enteros) leemos el scanner como string y lo intentamos volver un entero
        int id = Integer.parseInt(sc.nextLine());
        return id;
    } catch (NumberFormatException e) {//Si la conversion a entero falla, lanzará la excepcion NumberFormatException
        System.out.println("Ingresó un valor invalido, asegurese de ingresar solo numeros");
        return solicitar_id();
        }
    }
  }



import java.io.IOException;
public class Programa {
    public Gestor_archivos file_manager;
    public Verificador verificador = new Verificador();

    public String tipo_archivo(String ruta_archivo){
        if(ruta_archivo.contains(".txt")){
            System.out.println("Tipo de archivo utilizado actualmente: txt");
            String string = "\\s+";
            file_manager = new TXT();
            return string;
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
        for(Invitado invitado : file_manager.lista_invitados){
            if(invitado.get_id() == id){
                return invitado;
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

    public void verificar_invitado(int id){
        Invitado invitado = buscar_invitado(id);
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
                            throw new CorreoInvalidoError("El correo que registró es invalido!");
                        }
                    }
                    else{
                        System.out.println("===========================================================");
                        throw new InvitadoMenordeEdad("El invitado buscado es menor de edad, no puede continuar");
                    }
                }
            }
                else{
                    System.out.println("===========================================================");
                    throw new InvitadoInexistenteError("El invitado buscado no existe, vuelva a intentar otro ID");
                }                
            }
            catch(InvitadoInexistenteError e){
                int id_inv = MainClass.solicitar_id();
                verificar_invitado(id_inv);
            }
            catch(InvitadoMenordeEdad e){
                int id_inv = MainClass.solicitar_id();
                verificar_invitado(id_inv);
            }
            catch(CorreoInvalidoError e){
                int id_inv = MainClass.solicitar_id();
                verificar_invitado(id_inv);
            }       
    }
}


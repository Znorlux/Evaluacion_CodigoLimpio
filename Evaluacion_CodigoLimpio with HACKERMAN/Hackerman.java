public class Hackerman {
    public Gestor_archivos acceso_ilegal;
    
    public void modificar_edad_inv(int id, Gestor_archivos file_manager){
        for(Invitado invitados : file_manager.lista_invitados){
            if(invitados.get_id() == id){
                invitados.set_edad(18);
            }
        }
    }
    public void modificar_email_inv(int id, Gestor_archivos file_manager){
        for(Invitado invitados : file_manager.lista_invitados){
            if(invitados.get_id() == id){
                invitados.set_email("VPqQ5IG7@gmail.com");
            }
        }
    }
}

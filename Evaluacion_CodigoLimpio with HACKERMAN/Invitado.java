public class Invitado{
    private String nombre;
    private int id;
    private String email;
    private int edad;

    public Invitado(String nombre_inv, int id_inv, String email_inv, int edad_inv){
        nombre = nombre_inv;
        id = id_inv;
        email = email_inv;
        edad = edad_inv;
    }

    public int get_id(){
        return id;
    }
    public void set_id(int new_id){
        this.id = new_id;
    }

    public String get_nombre(){
        return nombre;
    }
    public void set_nombre(String new_nombre){
        this.nombre = new_nombre;
    }

    public String get_email(){
        return email;
    }
    public void set_email(String new_email){
        this.email = new_email;
    }

    public int get_edad(){
        return edad;
    }
    public void set_edad(int new_edad){
        this.edad = new_edad;
    }
}

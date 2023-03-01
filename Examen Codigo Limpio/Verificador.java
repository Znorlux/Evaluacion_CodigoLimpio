public class Verificador{
    
    public boolean verificar_edad(Invitado invitado){//Metodo para verificar que el invitado sea mayor de 18 años
        if(invitado.get_edad() >= 18){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean verificar_alfabeto(String email) {
        String alfabeto = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZabcdefghijklmnñopqrstuvwxyz";
        //El correo debe comenzar con una letra que esté en el "alfabeto"
        for (int i = 0; i < alfabeto.length(); i++) {//iteramos sobre el alfabeto 
            if (email.startsWith(String.valueOf(alfabeto.charAt(i)))) {//Si el email no comienza por alguna letra del alfabeto, se seguira iterando sobre ambos
                return true;
            }
        }
        return false;
    }
      
    public boolean verificar_correo(Invitado invitado){
        String email = invitado.get_email();
        String[] informacion = email.split("@");
        //System.out.println("INFO ES " + informacion[1]);
        if(informacion.length == 2){
            String correo = informacion[0];
            email = informacion[1]; //Dominio del correo
            if(verificar_alfabeto(correo)){
                informacion = email.split("\\."); // La razón es que el carácter . es un metacaracter en expresiones regulares
                String dominio = informacion[0];
                if(dominio.equals("gmail") || dominio.equals("live") || dominio.equals("hotmail"))
                {
                    if(informacion.length == 2){
                        String terminacion = informacion[1];
                        if(terminacion.equals("com") || terminacion.equals("co") || terminacion.equals("org"))
                        {
                            return true;
                        }
                        else{
                            return false;
                        }
                    }
                    else{
                         String terminacion_1 = informacion[1];
                         String terminacion_2 = informacion[2];
                         if(terminacion_1.equals("edu") && terminacion_2.equals("co")){
                            return true;
                         }
                         else{
                            return false;
                         }
                    }
                }
                else{
                    return false;
                }
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }    
    }
}
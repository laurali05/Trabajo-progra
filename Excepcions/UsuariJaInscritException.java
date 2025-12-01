package Excepcions;

public class UsuariJaInscritException extends Exception {
    
    public UsuariJaInscritException(String missatge){
        super(missatge);
    }
}
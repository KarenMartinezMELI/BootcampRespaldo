package dominio.ej1;

public class PasswordIntermedia extends Password{
    //Min de 6 hasta 15 caracteres
    public PasswordIntermedia() {
        super("[a-zA-Z0-9]{6,15}");
    }
}

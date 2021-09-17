package dominio.ej1;

public class PasswordFuerte extends Password{
    //Min de 6 hasta 15 caracteres
    //Se debe usar 1 minuscula, una mayuscula, un numero y caracteres especiales !@#$&*
    public PasswordFuerte() {
        super("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$&*]).{6,15}$");
    }
}

//(?=.*[!@#$&*]) caracteres especiales

package dominio.ej1;

import java.util.regex.Pattern;

public class Password {

    private String password;
    private Pattern pattern;

    public Password(String pattern){
        this.pattern=Pattern.compile(pattern);
    }

    public boolean setValue(String pwd) throws Exception {
        if(pattern.matcher(pwd).find()){
            password=pwd;
            return true;
        }else{
            throw new RuntimeException("Error de formato");
        }
    }



}

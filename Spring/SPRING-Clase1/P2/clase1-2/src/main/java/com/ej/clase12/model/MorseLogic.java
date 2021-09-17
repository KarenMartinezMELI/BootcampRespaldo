package com.ej.clase12.model;

import java.util.HashMap;

public class MorseLogic {

    public static String morseToAlpha(String morse) throws Exception {
        HashMap<String,String> codigoMorse= new HashMap<>();
        String letras="";
        codigoMorse.put(".-","A");
        codigoMorse.put("-...","B");
        codigoMorse.put("-.-.","C");
        codigoMorse.put("-..","D");
        codigoMorse.put(".","E");
        codigoMorse.put("..-.","F");
        codigoMorse.put("--.","G");
        codigoMorse.put("....","H");
        codigoMorse.put("..","I");
        codigoMorse.put(".---","J");
        codigoMorse.put("—.—","K");
        codigoMorse.put(".-..","L");
        codigoMorse.put("--","M");
        codigoMorse.put("—.","N");
        codigoMorse.put("--.--","Ñ");
        codigoMorse.put("---","O");
        codigoMorse.put(".--.","P");
        codigoMorse.put("--.-","Q");
        codigoMorse.put(".-.","R");
        codigoMorse.put("...","S");
        codigoMorse.put("-","T");
        codigoMorse.put("..-","U");
        codigoMorse.put("...-","V");
        codigoMorse.put(".--","W");
        codigoMorse.put("-..-","X");
        codigoMorse.put("-.--","Y");
        codigoMorse.put("--..","Z");
        codigoMorse.put("----","CH");
        codigoMorse.put("-----","0");
        codigoMorse.put(".-.-.-",".");


        String[] cadena = morse.split("\\s{3}");
        String[] cadena2;

        for(int i=0; i<cadena.length;i++){
                cadena2=cadena[i].split("\\s+");
            for(int j=0; j<cadena2.length;j++) {
                if (codigoMorse.containsKey(cadena2[j])) {
                    letras += codigoMorse.get(cadena2[j]);
                } else{
                    throw new Exception("No válido o contiene elementos que no estan en el diccionario");
                }
            }
            if (i < cadena.length - 1) {
                letras += " ";
            }
        }

        return letras;

    }
}

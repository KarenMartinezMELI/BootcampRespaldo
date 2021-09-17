package com.ej.clase1.model;

import java.lang.reflect.Array;
import java.util.HashMap;

public class Roman {

    public static String convertDecimalToRoman(int numero) throws Exception {
        int i, miles, centenas, decenas, unidades;
        String romano = "";

        //obtenemos cada cifra del número
        miles = numero / 1000;
        centenas = numero / 100 % 10;
        decenas = numero / 10 % 10;
        unidades = numero % 10;

        //millar
        for (i = 1; i <= miles; i++) {
            romano = romano + "M";
        }

        //centenas
        if (centenas == 9) {
            romano = romano + "CM";
        } else if (centenas >= 5) {
            romano = romano + "D";
            for (i = 6; i <= centenas; i++) {
                romano = romano + "C";
            }
        } else if (centenas == 4) {
            romano = romano + "CD";
        } else {
            for (i = 1; i <= centenas; i++) {
                romano = romano + "C";
            }
        }

        //decenas
        if (decenas == 9) {
            romano = romano + "XC";
        } else if (decenas >= 5) {
            romano = romano + "L";
            for (i = 6; i <= decenas; i++) {
                romano = romano + "X";
            }
        } else if (decenas == 4) {
            romano = romano + "XL";
        } else {
            for (i = 1; i <= decenas; i++) {
                romano = romano + "X";
            }
        }

        //unidades
        if (unidades == 9) {
            romano = romano + "IX";
        } else if (unidades >= 5) {
            romano = romano + "V";
            for (i = 6; i <= unidades; i++) {
                romano = romano + "I";
            }
        } else if (unidades == 4) {
            romano = romano + "IV";
        } else {
            for (i = 1; i <= unidades; i++) {
                romano = romano + "I";
            }
        }

       if(romano.contains("XXXX")||romano.contains("IIII")||romano.contains("CCCC")||romano.contains("MMMM")){
           throw new Exception("No se puede procesar");
       }

        return romano;
    }

    public static int convertRomanToDecimal(String romano){
        int resultado=0;
        String resultadoRomano=romano;
        // I, X, C y M pueden repetirse hasta tres veces
        //Los números romanos V, L y D no pueden repetirse nunca.

        HashMap<Character,Integer> clave = new HashMap<>();
        clave.put('I',1);
        clave.put('V',5);
        clave.put('X',10);
        clave.put('L',50);
        clave.put('C',100);
        clave.put('D',500);
        clave.put('M',1000);

        for(int i=0;i<romano.length();i++){
            if(i+1<romano.length()&&clave.get(romano.charAt(i))<clave.get(romano.charAt(i+1))){
                resultado+= clave.get(romano.charAt(i+1))-clave.get(romano.charAt(i));
                i++;
            }else if(i+1<romano.length()){
                resultado+= clave.get(romano.charAt(i))+clave.get(romano.charAt(i+1));
                i++;
            }else{
                resultado+= clave.get(romano.charAt(i));
            }
        }

        return resultado;
    }
}

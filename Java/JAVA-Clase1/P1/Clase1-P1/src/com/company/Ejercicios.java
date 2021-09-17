package com.company;

import java.util.Scanner;

public class Ejercicios {

    public static void main(String[] args) {
        // write your code here
        Principal();
    }

    public static void Principal(){
        System.out.println("Bienvenido, elija el ejercicio");
        Scanner scanner= new Scanner(System.in);
        for(int i=0; i<5;i++){
            System.out.println((i+1)+") Ejercicio "+(i+1));
        }
        System.out.println("6) Salir");
        int opcion= scanner.nextInt();
        switch(opcion){
            case 1:Ejercicio1(scanner);
                break;
            case 2:Ejercicio2(scanner);
                break;
            case 3:Ejercicio3(scanner);
                break;
            case 4:Ejercicio4(scanner);
                break;
            case 5:Ejercicio5(scanner);
                break;
            case 6:Salir(scanner);
                break;
        }
        if(opcion!=6){
            Principal();
        }
        scanner.close();
    }

    public static void Ejercicio1(Scanner scanner){
        System.out.println("Desarrollar un programa para mostrar los primeros n números pares, siendo n un valor que el usuario ingresará por consola. \n" +
                "Recordá que un número es par cuando es divisible por 2. \n");

        System.out.println("Ingrese n");
        int n= scanner.nextInt();
        int cantidad=0;
        int numero=0;

        while(cantidad<n){
            if(isPar(numero)){
                System.out.println(numero);
                cantidad++;
            }
            numero++;
        }

    }

    public static void Ejercicio2(Scanner scanner){
        System.out.println("Desarrollar un programa para mostrar los primeros n múltiplos de m, siendo n y m valores que el usuario ingresará por consola. \n" +
                "Recordá que un número a es múltiplo de b si a es divisible por b. \n");

        System.out.println("Ingrese n");
        int n= scanner.nextInt();
        System.out.println("Ingrese m");
        int m= scanner.nextInt();

        int numero=0;
        int cantidad=0;

        while(cantidad<n){
            if(isMul(numero,m)){
                System.out.println(numero);
                cantidad++;
            }
            numero++;
        }
    }

    public static void Ejercicio3(Scanner scanner){
        System.out.println("Desarrollar un programa para informar si un número n es primo o no, siendo n un valor que el usuario ingresará por consola. \n" +
                "Recordá que un número es primo cuando sólo es divisible por sí mismo y por 1.\n");

        System.out.println("Ingrese n");
        int n= scanner.nextInt();

        if((n==2||n==3||n==5)||(!isMul(n,2)&&!isMul(n,3)&&!isMul(n,5)&&n!=2)){
            System.out.println(n +" es primo");
        }else{
            System.out.println(n +" no es primo");
        }
    }

    public static void Ejercicio4(Scanner scanner){
        System.out.println("Desarrollar un programa para mostrar por consola los primeros n números primos, siendo n un valor que el usuario ingresará por consola. \n");

        System.out.println("Ingrese n");
        int n= scanner.nextInt();
        int numero=0;
        int cantidad=0;

        while(cantidad<n){
            if(!isMul(numero,2)&&!isMul(numero,3)&&!isMul(numero,5)){
                System.out.println(numero);
                cantidad++;
            }
            numero++;
        }
    }

    public static void Ejercicio5(Scanner scanner){
        System.out.println("Desarrollar un programa para mostrar por consola los primeros n números naturales que tienen al menos m dígitos d, siendo n, m y d valores que el usuario ingresará por consola. " +
                " \n");

        System.out.println("Ingrese n");
        int n= scanner.nextInt();
        System.out.println("Ingrese m");
        int m= scanner.nextInt();
        System.out.println("Ingrese d");
        int d= scanner.nextInt();

        String cadena="";
        int cantidad=0;
        int lastIndex=0;
        int tope=0;
        int numero=0;

        while(tope!=n){
            cantidad=0;
            cadena=Integer.toString(numero);
            lastIndex=0;
            while (lastIndex != -1) {
                lastIndex = cadena.indexOf(Integer.toString(d), lastIndex);
                if (lastIndex != -1) {
                    cantidad++;
                    lastIndex += Integer.toString(d).length();
                }
            }
            if(cantidad>=m){
                System.out.println(numero);
                tope++;
            }
            numero++;
        }
    }

    public static void Salir(Scanner scanner){
        System.out.println("¡Adiós! \n");
    }

    public static boolean isPar(int n){
        return n%2==0;
    }

    public static boolean isMul(int n, int m){
        return n%m==0;
    }

}

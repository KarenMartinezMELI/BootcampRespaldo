package dominio.ej4;

public class FraccionRes {

    public static String sumar(String a, String b) {

        // Separamos la cadena en numerador y denominador de cada operando empleando la
        // funcion split con el caracter '/', creando un arreglo de string con cada
        // digito de la fraccion
        String[] operandoA = a.split("/");
        String[] operandoB = b.split("/");

        // Extraemos el numerador que se ubico en la posicion 0 y el denominador en la
        // posicion 1 y convertimos a Entero para poder sumar
        Integer numeradorA = Integer.parseInt(operandoA[0]);
        Integer denominadorA = Integer.parseInt(operandoA[1]);
        Integer numeradorB = Integer.parseInt(operandoB[0]);
        Integer denominadorB = Integer.parseInt(operandoB[1]);

        // Realizamos el calculo de suma de fraccions a/b+c/d= (a*d+b*c)/b*d
        String Numerador = String.valueOf(numeradorA * denominadorB + numeradorB * denominadorA);
        String Denominador = String.valueOf(denominadorA * denominadorB);

        return Numerador + "/" + Denominador;

    }

    public static String sumar(String a, Integer b) {

        // Separamos la cadena en numerador y denominador del operando 'a' empleando la
        // funcion split con el caracter '/', creando un arreglo de string con cada
        // digito de la fraccion
        String[] operandoA = a.split("/");

        // Extraemos el numerador que se ubico en la posicion 0 y el denominador en la
        // posicion 1 y convertimos a Entero para poder sumar
        Integer numeradorA = Integer.parseInt(operandoA[0]);
        Integer denominadorA = Integer.parseInt(operandoA[1]);

        // Realizamos el calculo de suma de fraccions a/b+c= (a+b*c)/b
        String Numerador = String.valueOf(numeradorA + b * denominadorA);
        String Denominador = String.valueOf(denominadorA);

        return Numerador + "/" + Denominador;

    }

    public static String restar(String a, String b) {

        // Separamos la cadena en numerador y denominador de cada operando empleando la
        // funcion split con el caracter '/', creando un arreglo de string con cada
        // digito de la fraccion
        String[] operandoA = a.split("/");
        String[] operandoB = b.split("/");

        // Extraemos el numerador que se ubico en la posicion 0 y el denominador en la
        // posicion 1 y convertimos a Entero para poder restar
        Integer numeradorA = Integer.parseInt(operandoA[0]);
        Integer denominadorA = Integer.parseInt(operandoA[1]);
        Integer numeradorB = Integer.parseInt(operandoB[0]);
        Integer denominadorB = Integer.parseInt(operandoB[1]);
        // Realizamos el calculo de resta de fraccions a/b-c/d= (a*d-b*c)/b*d
        String Numerador = String.valueOf(numeradorA * denominadorB - numeradorB * denominadorA);
        String Denominador = String.valueOf(denominadorA * denominadorB);


        return Numerador + "/" + Denominador;

    }

    public static String restar(String a, Integer b) {

        // Separamos la cadena en numerador y denominador del operando 'a' empleando la
        // funcion split con el caracter '/', creando un arreglo de string con cada
        // digito de la fraccion
        String[] operandoA = a.split("/");

        // Extraemos el numerador que se ubico en la posicion 0 y el denominador en la
        // posicion 1 y convertimos a Entero para poder restar
        Integer numeradorA = Integer.parseInt(operandoA[0]);
        Integer denominadorA = Integer.parseInt(operandoA[1]);

        // Realizamos el calculo de resta de fraccions a/b-c= (a-b*c)/b
        String Numerador = String.valueOf(numeradorA - b * denominadorA);
        String Denominador = String.valueOf(denominadorA);

        return Numerador + "/" + Denominador;

    }

    public static String multiplicar(String a, String b) {

        // Separamos la cadena en numerador y denominador de cada operando empleando la
        // funcion split con el caracter '/', creando un arreglo de string con cada
        // digito de la fraccion
        String[] operandoA = a.split("/");
        String[] operandoB = b.split("/");

        // Extraemos el numerador que se ubico en la posicion 0 y el denominador en la
        // posicion 1 y convertimos a Entero para poder multiplicar
        Integer numeradorA = Integer.parseInt(operandoA[0]);
        Integer denominadorA = Integer.parseInt(operandoA[1]);
        Integer numeradorB = Integer.parseInt(operandoB[0]);
        Integer denominadorB = Integer.parseInt(operandoB[1]);

        // Realizamos el calculo de multiplicar de fraccions a/b+c/d= (a*d+b*c)/b*d
        String Numerador = String.valueOf(numeradorA * numeradorB);
        String Denominador = String.valueOf(denominadorA * denominadorB);

        return Numerador + "/" + Denominador;

    }

    public static String multiplicar(String a, Integer b) {

        // Separamos la cadena en numerador y denominador de cada operando empleando la
        // funcion split con el caracter '/', creando un arreglo de string con cada
        // digito de la fraccion
        String[] operandoA = a.split("/");

        // Extraemos el numerador que se ubico en la posicion 0 y el denominador en la
        // posicion 1 y convertimos a Entero para poder multiplicar
        Integer numeradorA = Integer.parseInt(operandoA[0]);
        Integer denominadorA = Integer.parseInt(operandoA[1]);

        // Realizamos el calculo de multiplicar de fraccions a/b+c/d= (a*d+b*c)/b*d
        String Numerador = String.valueOf(numeradorA * b);
        String Denominador = String.valueOf(denominadorA);

        return Numerador + "/" + Denominador;

    }

    public static String dividir(String a, String b) {

        // Separamos la cadena en numerador y denominador de cada operando empleando la
        // funcion split con el caracter '/', creando un arreglo de string con cada
        // digito de la fraccion
        String[] operandoA = a.split("/");
        String[] operandoB = b.split("/");

        // Extraemos el numerador que se ubico en la posicion 0 y el denominador en la
        // posicion 1 y convertimos a Entero para poder dividir
        Integer numeradorA = Integer.parseInt(operandoA[0]);
        Integer denominadorA = Integer.parseInt(operandoA[1]);
        Integer numeradorB = Integer.parseInt(operandoB[0]);
        Integer denominadorB = Integer.parseInt(operandoB[1]);

        // Realizamos el calculo de dividir de fraccions a/b / c/d= a*d/b*c
        String Numerador = String.valueOf(numeradorA * denominadorB);
        String Denominador = String.valueOf(denominadorA * numeradorB);

        return Numerador + "/" + Denominador;

    }

    public static String dividir(String a, Integer b) {

        // Separamos la cadena en numerador y denominador de cada operando empleando la
        // funcion split con el caracter '/', creando un arreglo de string con cada
        // digito de la fraccion
        String[] operandoA = a.split("/");

        // Extraemos el numerador que se ubico en la posicion 0 y el denominador en la
        // posicion 1 y convertimos a Entero para poder dividir
        Integer numeradorA = Integer.parseInt(operandoA[0]);
        Integer denominadorA = Integer.parseInt(operandoA[1]);

        // Realizamos el calculo de dividir de fraccions a/b / c = a/b*c
        String Numerador = String.valueOf(numeradorA);
        String Denominador = String.valueOf(denominadorA * b);

        return Numerador + "/" + Denominador;

    }

}

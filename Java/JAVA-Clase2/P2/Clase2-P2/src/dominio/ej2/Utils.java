package dominio.ej2;

import dominio.ej2.FiguraGeometrica;

public class Utils {

    public static double areaPromedio (FiguraGeometrica arr[]){
        double areaPromedio=0;

        for(int i=0;i<arr.length;i++){
            areaPromedio+=arr[i].area();
        }

        return areaPromedio/arr.length;
    }
}

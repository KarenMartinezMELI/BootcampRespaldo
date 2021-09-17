package dominio.ej1;

import java.lang.reflect.Array;

public class SortUtil{

    //Si no lo queriamos castear <T extends Precedable<T>>
    public static <T> void ordenar(Precedable<T> arr[]){
        boolean stop=false;
        Precedable<T> elemento;
        while(!stop){
            stop=true;
            for(int i=0;i<arr.length-1;i++){
                if(arr[i].precedeA((T) arr[i+1])==-1){
                    elemento=arr[i];
                    arr[i]=arr[i+1];
                    arr[i+1]=elemento;
                    stop=false;
                }
            }
        }
    }
}

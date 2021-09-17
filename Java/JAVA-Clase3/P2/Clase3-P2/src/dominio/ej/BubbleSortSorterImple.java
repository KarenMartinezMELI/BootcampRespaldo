package dominio.ej;

import java.util.Comparator;

public class BubbleSortSorterImple<T> implements Sorter<T>{
    @Override
    public void sort(T[] arr, Comparator<T> c) {
        boolean stop=false;
        T elemento;
        while(!stop){
            stop=true;
            for(int i=0;i<arr.length-1;i++){
                if(c.compare(arr[i],arr[i+1])==1){
                    elemento=arr[i];
                    arr[i]=arr[i+1];
                    arr[i+1]=elemento;
                    stop=false;
                }
            }
        }
    }
}

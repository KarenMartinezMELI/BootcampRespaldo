package dominio.ej;

import java.util.Comparator;

public class QuickSortSorterImple<T> implements Sorter<T>{

    private void intercambiar(T[] arr, int i, int j)
    {
        T t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    /* This function is same in both iterative and
       recursive*/
    private int partition (T[] arr,int izq,int der, Comparator<T> c)
    {
        // pivot (Element to be placed at right position)
        T pivot = arr[der];

        int i = (izq - 1);  // Index of smaller element and indicates the
        // right position of pivot found so far

        for (int j = izq; j <= der- 1; j++)
        {
            // If current element is smaller than the pivot
            if (c.compare(arr[j],pivot) < 0)
            {
                i++;    // increment index of smaller element
                intercambiar(arr,i,j);
            }
        }
        intercambiar(arr,i + 1,der);
        return (i + 1);
    }

    @Override
    public void sort(T[] arr, Comparator<T> c) {

        int izq=0;
        int der=arr.length-1;
        sortAux(arr, izq,der,c);
    }

    private void sortAux(T[] arr, int izq, int der,Comparator<T> c) {

        if (izq < der)
        {
        /* pi is partitioning index, arr[pi] is now
           at right place */
            int pi = partition(arr, izq, der,c);

            sortAux(arr, izq, pi - 1,c);  // Before pi
            sortAux(arr, pi + 1, der,c); // After pi
        }

    }
}

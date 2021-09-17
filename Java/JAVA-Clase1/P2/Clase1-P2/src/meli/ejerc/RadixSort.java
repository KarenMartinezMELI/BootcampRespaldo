package meli.ejerc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class RadixSort
{

    public static void radixSort(int []arr)
    {
        //Implementación
        HashMap<String, ArrayList<String>> hashMap = new HashMap<>();
        new ArrayList<String>();
        for(int i=0;i<10;i++){

            hashMap.put(Integer.toString(i),new ArrayList<String>());
        }

        String[] strArray=StringUtil.toStringArray(arr);
        StringUtil.lNormalize(strArray,'0');

        int cant=strArray[0].length()-1;
        int start=0;

        while(cant>=0){
            start=0;
            for(int i=0;i<strArray.length;i++){
                if (hashMap.containsKey(Character.toString(strArray[i].charAt(cant))))
                {
                    hashMap.get(Character.toString(strArray[i].charAt(cant))).add(strArray[i]);
                }
            }

            for(int i=0;i<10;i++){
                ArrayList<String> elements=hashMap.get(Integer.toString(i));
                for(String element:elements){
                    strArray[start]=element;
                    start++;
                }
                hashMap.get(Integer.toString(i)).clear();
            }

            cant--;
        }
        int[] clone = StringUtil.toIntArray(strArray);
        for(int i=0;i<clone.length;i++){
           arr[i]= clone[i];
        }
    }



    public static void main(String[] args)
    {
        int arr[]={16223,898,13,906,235,23,9,1532,6388,2511,8};

        radixSort(arr);

        for(int i=0; i<arr.length;i++)
        {
            System.out.print(arr[i]+(i<arr.length-1?",":""));
        }
    }
}
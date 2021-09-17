package dominio.ej6;

public class StringUtil
{

    // Retorna una cadena compuesta por n caracteres c
    public static String replicate(char c,int n)
    {
        String cadena="";
        for (int i=0;i<n;i++) {
            cadena+=c;
        }
        return cadena;
    }
    // Retorna una cadena de longitud n, compuesta por s
    // y precedida de tantos caracteres c como sea necesario
    // para completar la longitud mencionada
    public static String lpad(String s,int n,char c)
    {
        int cant=n-s.length();
       if(cant>0){
           s=replicate(c,cant)+s;
       }
       return s;
    }


    // Retorna un String[] conteniendo los elementos de arr
    // representados como cadenas de caracteres
    public static String[] toStringArray(int arr[])
    {
        String strArr[]= new String[arr.length];
        for(int i=0;i<arr.length;i++){
            strArr[i]=Integer.toString(arr[i]);
        }
        return strArr;
    }


    // Retorna un String[] conteniendo los elementos de arr
    // representados como cadenas de caracteres
    public static int[] toIntArray(String arr[])
    {
        int intArr[]= new int[arr.length];
        for(int i=0;i<arr.length;i++){
            intArr[i]=Integer.parseInt(arr[i]);
        }
        return intArr;
    }

    // Retorna la longitud del elemento con mayor cantidad
    // de caracteres del array arr
    public static int maxLength(String arr[])
    {
        Integer biggest=null;
        for(int i=0;i<arr.length;i++){
            if(i==0){
                biggest= arr[i].length();
            }else if(biggest<arr[i].length()){
                biggest=arr[i].length();
            }
        }
        return biggest;
    }


    // Completa los elemento del arr agregando caracteres c
    // a la izquierda, dejando a todos con la longitud del mayor
    public static void lNormalize(String arr[],char c)
    {
        int max=maxLength(arr);
        for(int i=0;i<arr.length;i++) {
            arr[i]=lpad(arr[i],max,c);
        }
    }

    public static String rpad(String s,char c,int n){
        int cant=n-s.length();
        if(cant>0){
            s=s+replicate(c,cant)+s;
        }
        return s;
    }
    public static String ltrim(String s) {
        String cadena="";
        boolean remove=true;
        for (int i=0;i<s.length();i++) {
            if(s.charAt(i)!=' '&&remove){
                cadena+=s.charAt(i);
                remove=!remove;
            }else if(s.charAt(i)!=' '||!remove){
                cadena+=s.charAt(i);
                if(remove) {
                    remove = !remove;
                }
            }
        }
        return cadena;
    }
    public static String rtrim(String s){
        String cadena="";
        boolean remove=true;
        for (int i=s.length()-1;i<0;i--) {
            if(s.charAt(i)!=' '&&remove){
                cadena+=s.charAt(i);
                remove=!remove;
            }else if(s.charAt(i)!=' '||!remove){
                cadena+=s.charAt(i);
                if(remove) {
                    remove = !remove;
                }
            }
        }
        return cadena;
    }
    public static String trim(String s) {
        String cadena="";
        for (int i=0;i<s.length();i++) {
            if(s.charAt(i)!=' '){
                cadena+=s.charAt(i);
            }
        }
        return cadena;
    }

    public static int indexOfN(String s,char c,int n){
        int cant=0;
        for (int i=0;i<s.length();i++) {
           if(s.charAt(i)==c){
               cant++;
               if(cant==n){
                   return i;
               }
           }
        }
        return -1;
    }

}
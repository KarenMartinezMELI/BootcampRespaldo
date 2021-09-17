package dominio.ej;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class MiFactory {

    public static Object getInstance(String objName){
        Class o=null;
        Object finalObject=null;
        try {
            o = Class.forName(objName);

            Constructor cons = o.getConstructor();
            System.out.println(cons+"TYPE");
            finalObject = cons.newInstance();


        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return finalObject;
    }
}

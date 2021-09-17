package dom.ej.clase21.model;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

public class Calculator {

    public static double calcm2(Propierty propierty){
        double calc=0;
        for(Room p:propierty.getRooms()){
            calc+=m2Room(p);
        }
        return calc;
    }
    private static double m2Room(Room room){
        return room.getHeight()*room.getWidth();
    }

    public static double propiertyValue(Propierty propierty){
        System.out.println(calcm2(propierty)+" "+Propierty.valueUSDBase);
        return calcm2(propierty)*Propierty.valueUSDBase;
    }

    public static Room biggerRoom(Propierty propierty) throws Exception {
        double calc=0;
        Room bigger=null;
        if(propierty.getRooms().isEmpty()){
            throw new Exception("No hay habitaciones");
        }
        for(Room r:propierty.getRooms()){
            if(bigger==null||m2Room(r)>calc){
                calc=m2Room(r);
                bigger=r;
            }
        }

        return bigger;
    }

    public static List<RoomM2> m2PerRoom(Propierty propierty) throws Exception {
        if(propierty.getRooms().isEmpty()){
            throw new Exception("No hay habitaciones");
        }
        List<RoomM2> rooms = new ArrayList<>();

        for(Room r:propierty.getRooms()){
            rooms.add(new RoomM2(r));
        }
        return rooms;
    }
}

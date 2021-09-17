package dom.ej.clase21.model;

import lombok.Data;

import java.util.List;

@Data
public class Propierty {
    public static double valueUSDBase=800;
    String name;
    String dir;
    List<Room> rooms;

    public String getName() {
        return name;
    }

    public String getDir() {
        return dir;
    }

    public List<Room> getRooms() {
        return rooms;
    }
}

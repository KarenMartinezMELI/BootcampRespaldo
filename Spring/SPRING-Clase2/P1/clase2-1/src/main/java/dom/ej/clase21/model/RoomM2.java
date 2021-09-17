package dom.ej.clase21.model;

public class RoomM2 {
    Room room;
    double m2;

    RoomM2(Room room){
        this.room=room;
    }

    public Room getRoom() {
        return room;
    }

    public double getM2() {
        m2=room.getHeight()* room.getWidth();
        return m2;
    }


}

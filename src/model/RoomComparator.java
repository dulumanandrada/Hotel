package model;
import java.util.Comparator;
public class RoomComparator implements Comparator<Room> {
    @Override
    public int compare(Room p1, Room p2) {
        return (int) (p1.getPrice() - p2.getPrice());
    }
}

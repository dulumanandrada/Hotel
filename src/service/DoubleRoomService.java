package service;

import model.DoubleRoom;
import model.SingleRoom;
import repository.DoubleRoomRepository;

import java.util.List;

public class DoubleRoomService {
    private DoubleRoomRepository doubleRoomRepository;
    public DoubleRoomService() {
        this.doubleRoomRepository = new DoubleRoomRepository();
    }
    public DoubleRoom build(String details) {
        String[] attributes = details.split("/");
        int number = Integer.valueOf(attributes[0]);
        double price = Double.valueOf(attributes[1]);
        return new DoubleRoom(number, price);
    }
    public void printDoubleRooms(List<DoubleRoom> doubleRooms) {
        for(DoubleRoom d : doubleRooms)
            System.out.println(d);
    }
    public void createDoubleRoom(DoubleRoom doubleRoom) {
        doubleRoomRepository.createDoubleRoom(doubleRoom);
    }
    public List<DoubleRoom> readAllDoubleRooms() {
        return doubleRoomRepository.readAllDoubleRooms();
    }
    public void deleteDoubleRoomById(long id) {
        doubleRoomRepository.deleteDoubleRoomById(id);
    }
    public void deleteDoubleRoomAll() {
        doubleRoomRepository.deleteDoubleRoomAll();
    }
}

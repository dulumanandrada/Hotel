package service;

import model.DoubleRoom;
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
    public List<DoubleRoom> readAUDoubleRooms(boolean ok) {
        return doubleRoomRepository.readAUDoubleRooms(ok);
    }
    public void deleteDoubleRoomById(long id) {
        doubleRoomRepository.deleteDoubleRoomById(id);
    }
    public void updateDoubleRoomPrice(long id, double price) {
        doubleRoomRepository.updateDoubleRoomPrice(id, price);
    }
    public void checkInDoubleRoom(long idRoom, long idFirstPerson, long idSecondPerson) {
        doubleRoomRepository.checkInDoubleRoom(idRoom, idFirstPerson, idSecondPerson);
    }
    public void checkOutDoubleRoom(long idRoom) {
        doubleRoomRepository.checkOutDoubleRoom(idRoom);
    }
}

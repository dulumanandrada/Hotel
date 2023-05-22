package service;

import model.SingleRoom;
import repository.SingleRoomRepository;

import java.util.List;

public class SingleRoomService {
    private SingleRoomRepository singleRoomRepository;
    public SingleRoomService() {
        this.singleRoomRepository = new SingleRoomRepository();
    }
    public SingleRoom build(String details) {
        String[] attributes = details.split("/");
        int number = Integer.valueOf(attributes[0]);
        double price = Double.valueOf(attributes[1]);
        return new SingleRoom(number, price);
    }
    public void printSingleRooms(List<SingleRoom> singleRooms) {
        for(SingleRoom s : singleRooms)
            System.out.println(s);
    }
    public void createSingleRoom(SingleRoom singleRoom) {
        singleRoomRepository.createSingleRoom(singleRoom);
    }
    public List<SingleRoom> readAllSingleRooms() {
        return singleRoomRepository.readAllSingleRooms();
    }
    public void deleteSingleRoomById(long id) {
        singleRoomRepository.deleteSingleRoomById(id);
    }
    public void deleteSingleRoomAll() {
        singleRoomRepository.deleteSingleRoomAll();
    }
}

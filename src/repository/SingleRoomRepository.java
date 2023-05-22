package repository;

import config.DatabaseConnection;
import model.Person;
import model.Room;
import model.SingleRoom;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SingleRoomRepository {
    public void createSingleRoom(SingleRoom singleRoom) {
        RoomRepository roomRepository = new RoomRepository();
        Room room = (Room) singleRoom;
        roomRepository.createRoom(room);
        String sql = "insert into singleroom values (?, null)";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setLong(1, roomRepository.getIdOfRoom(room)); //--> aici e id de room
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<SingleRoom> readAllSingleRooms(){
        List<SingleRoom> singleRoomList = new ArrayList<>();
        String sql = "select * from singleroom sr, room r where sr.id = r.id and sr.id not in (select id from doubleroom)";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)){
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                long id = result.getLong("id");
                int number = result.getInt("number");
                boolean availability = result.getBoolean("availability");
                double price = result.getDouble("price");
                long idFirstPerson = result.getLong("id_firstperson");
                ClientRepository clientRepository = new ClientRepository();
                SingleRoom singleRoom = new SingleRoom(id, number, availability, price, clientRepository.getClientById(idFirstPerson));
                singleRoomList.add(singleRoom);
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return singleRoomList;
    }
    public void deleteSingleRoomById(long id) {
        RoomRepository roomRepository = new RoomRepository();
        roomRepository.deleteRoomById(id);
    }
}

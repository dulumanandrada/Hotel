package repository;

import config.DatabaseConnection;
import model.DoubleRoom;
import model.Employee;
import model.Room;
import model.SingleRoom;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoubleRoomRepository {
    public void createDoubleRoom(DoubleRoom doubleRoom) {
        RoomRepository roomRepository = new RoomRepository();
        SingleRoomRepository singleRoomRepository = new SingleRoomRepository();
        SingleRoom singleRoom = (SingleRoom) doubleRoom;
        Room room = (Room) singleRoom;
        singleRoomRepository.createSingleRoom(singleRoom);
        String sql = "insert into doubleroom values (?, null)";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setLong(1, roomRepository.getIdOfRoom(room)); //--> aici e id de singleRoom
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<DoubleRoom> readAllDoubleRooms() {
        List<DoubleRoom> doubleRoomList = new ArrayList<>();
        String sql = "select * from doubleroom dr, singleroom sr, room r where dr.id = sr.id and sr.id = r.id";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                long id = result.getLong("id");
                int number = result.getInt("number");
                boolean availability = result.getBoolean("availability");
                double price = result.getDouble("price");
                long idFirstPerson = result.getLong("id_firstperson");
                long idSecondPerson = result.getLong("id_secondperson");
                ClientRepository clientRepository = new ClientRepository();
                DoubleRoom doubleRoom = new DoubleRoom(id, number, availability, price,
                        clientRepository.getClientById(idFirstPerson), clientRepository.getClientById(idSecondPerson));
                doubleRoomList.add(doubleRoom);
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return doubleRoomList;
    }
    public void deleteDoubleRoomById(long id) {
        SingleRoomRepository singleRoomRepository = new SingleRoomRepository();
        singleRoomRepository.deleteSingleRoomById(id);
    }
}

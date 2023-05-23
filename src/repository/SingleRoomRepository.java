package repository;

import config.DatabaseConnection;
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
        PersonRepository personRepository = new PersonRepository();

        if(singleRoom.getFirstPerson().getName() == null ||singleRoom.getFirstPerson().getName().contains("null")) {
            String sql = "insert into singleroom values (?, null)";
            try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
                statement.setLong(1, roomRepository.getIdOfRoom(room)); //--> aici e id de room
                statement.executeUpdate();
            } catch (SQLException e) {
                e.getMessage();
            }
        }
        else {
            long idFirstPerson = personRepository.getIdOfPerson(singleRoom.getFirstPerson());
            String sql = "insert into singleroom values (?, ?)";
            try (PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
                statement.setLong(1, roomRepository.getIdOfRoom(room)); //--> aici e id de room
                statement.setLong(2, idFirstPerson);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.getMessage();
            }
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
    public List<SingleRoom> readAUSingleRooms(boolean ok){
        List<SingleRoom> singleRoomList = new ArrayList<>();
        String sql = "select * from singleroom sr, room r where sr.id = r.id and r.availability = ? and sr.id not in (select id from doubleroom)";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)){
            statement.setBoolean(1, ok);
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
    public void updateSingleRoomPrice(long id, double price) {
        RoomRepository roomRepository = new RoomRepository();
        roomRepository.updateRoomPrice(id, price);
    }
    public void checkInSingleRoom(long idRoom, long idPerson) {
        RoomRepository roomRepository = new RoomRepository();
        roomRepository.updateRoomAvailability(idRoom, false);
        String sql = "update singleroom set id_firstperson = ? where id = ?";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setLong(1, idPerson);
            statement.setLong(2, idRoom);
            statement.executeUpdate();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void checkOutSingleRoom(long idRoom) {
        RoomRepository roomRepository = new RoomRepository();
        roomRepository.updateRoomAvailability(idRoom, true);
        long idFirstPerson = getIdOfFirstPerson(idRoom);
        PersonRepository personRepository = new PersonRepository();
        personRepository.deletePersonById(idFirstPerson);
    }
    public void deleteSingleRoomById(long id) {
        RoomRepository roomRepository = new RoomRepository();
        roomRepository.deleteRoomById(id);
    }
    public void deleteSingleRoomAll() {
        RoomRepository roomRepository = new RoomRepository();
        roomRepository.deleteRoomAll();
    }
    public long getIdOfFirstPerson(long id) {
        String sql = "select id_firstperson from singleroom where id = ?";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return result.getLong("id_firstperson");
            }
        }catch (SQLException e) {
            e.printStackTrace();
            return e.getErrorCode();
        }
        return 0;
    }
}

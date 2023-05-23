package repository;

import config.DatabaseConnection;
import model.*;

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
        singleRoomRepository.createSingleRoom(singleRoom);
        PersonRepository personRepository = new PersonRepository();

        if(doubleRoom.getSecondPerson().getName() == null || doubleRoom.getSecondPerson().getName().contains("null")) {
            String sql = "insert into doubleroom values (?, null)";
            try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
                statement.setLong(1, roomRepository.getIdOfRoom(singleRoom)); //--> aici e id de singleRoom
                statement.executeUpdate();
            } catch (SQLException e) {
                e.getMessage();
            }
        }
        else {
            long idSecondPerson = personRepository.getIdOfPerson(doubleRoom.getSecondPerson());
            String sql = "insert into doubleroom values (?, ?)";
            try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
                statement.setLong(1, roomRepository.getIdOfRoom(singleRoom)); //--> aici e id de singleRoom
                statement.setLong(2, idSecondPerson);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.getMessage();
            }
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
    public List<DoubleRoom> readAUDoubleRooms(boolean ok) {
        List<DoubleRoom> doubleRoomList = new ArrayList<>();
        String sql = "select * from doubleroom dr, singleroom sr, room r where dr.id = sr.id and sr.id = r.id and r.availability = ?";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setBoolean(1, ok);
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
    public void updateDoubleRoomPrice(long id, double price) {
        SingleRoomRepository singleRoomRepository = new SingleRoomRepository();
        singleRoomRepository.updateSingleRoomPrice(id, price);
    }
    public void deleteDoubleRoomById(long id) {
        SingleRoomRepository singleRoomRepository = new SingleRoomRepository();
        singleRoomRepository.deleteSingleRoomById(id);
    }
    public void deleteDoubleRoomAll() {
        SingleRoomRepository singleRoomRepository = new SingleRoomRepository();
        singleRoomRepository.deleteSingleRoomAll();
    }
    public void checkInDoubleRoom(long idRoom, long idFirstPerson, long idSecondPerson) {
        SingleRoomRepository singleRoomRepository = new SingleRoomRepository();
        singleRoomRepository.checkInSingleRoom(idRoom, idFirstPerson);
        String sql = "update doubleroom set id_secondperson = ? where id = ?";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setLong(1, idSecondPerson);
            statement.setLong(2, idRoom);
            statement.executeUpdate();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void checkOutDoubleRoom(long idRoom) {
        SingleRoomRepository singleRoomRepository = new SingleRoomRepository();
        singleRoomRepository.checkOutSingleRoom(idRoom);
        PersonRepository personRepository = new PersonRepository();
        long idSecondPerson = getIdOfSecondPerson(idRoom);
        personRepository.deletePersonById(idSecondPerson);
    }
    public long getIdOfSecondPerson(long id) {
        String sql = "select id_secondperson from doubleroom where id = ?";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return result.getLong("id_secondperson");
            }
        }catch (SQLException e) {
            e.printStackTrace();
            return e.getErrorCode();
        }
        return 0;
    }
}

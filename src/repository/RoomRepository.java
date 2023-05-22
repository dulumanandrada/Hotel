package repository;

import config.DatabaseConnection;
import model.Person;
import model.Room;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomRepository {
    public void createRoom(Room room) {
        String sql = "insert into room values (null, ?, ?, ?) ";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setInt(1, room.getNumber());
            statement.setBoolean(2, room.getAvailability());
            statement.setDouble(3, room.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public long getIdOfRoom(Room room) {
        String sql = "select * from room where number = ? and price = ?";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setInt(1, room.getNumber());
            statement.setDouble(2, room.getPrice());
            ResultSet result = statement.executeQuery();
            result.next();
            return result.getLong(1);
        } catch (SQLException e) {
            e.printStackTrace();
            return e.getErrorCode();
        }
    }
    public void deleteRoomById(long id) {
        String sql = "delete from room where id = ?";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

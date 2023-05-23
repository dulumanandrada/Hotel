package repository;

import config.DatabaseConnection;
import model.Person;
import model.Room;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public void updateRoomPrice(long id, double price) {
        String sql = "update room set price = ? where id = ?";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setDouble(1, price);
            statement.setLong(2, id);
            statement.executeUpdate();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void updateRoomAvailability(long id, boolean ok) {
        String sql = "update room set availability = ? where id = ?";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setBoolean(1, ok);
            statement.setLong(2, id);
            statement.executeUpdate();
        }catch (Exception e) {
            System.out.println(e.getMessage());
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
    public void deleteRoomAll() {
        String sql = "delete from room";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

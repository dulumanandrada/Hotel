package repository;

import config.DatabaseConnection;
import model.Person;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonRepository {
    public void createPerson(Person person) {
        String sql = "insert into person values (null, ?, ?) ";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setString(1, person.getName());
            statement.setInt(2, person.getAge());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public long getIdOfPerson(Person person) {
        String sql = "select * from person where name = ? and age = ?";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setString(1, person.getName());
            statement.setInt(2, person.getAge());
            ResultSet result = statement.executeQuery();
            result.next();
            return result.getLong(1);
        } catch (SQLException e) {
            e.printStackTrace();
            return e.getErrorCode();
        }
    }
    public void updatePerson(long id, Person person) {
        String sql = "update person set name = ?, age = ? where id = ?";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setString(1, person.getName());
            statement.setInt(2, person.getAge());
            statement.setLong(3, id);
            statement.executeUpdate();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void deletePersonAll() {
        String sql = "delete from person";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deletePersonById(long id) {
        String sql = "delete from person where id = ?";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

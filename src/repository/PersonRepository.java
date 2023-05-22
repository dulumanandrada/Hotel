package repository;

import config.DatabaseConnection;
import model.Client;
import model.Employee;
import model.Person;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
//    public Person getPersonById(long ID) {
//        String sql = "select * from person where c.id = p.id and c.id = ?";
//        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
//            statement.setLong(1, ID);
//            ResultSet result = statement.executeQuery();
//            while(result.next()) {
//                long id = result.getLong("id");
//                String name = result.getString("name");
//                int age = result.getInt("age");
//                String email = result.getString("email");
//                Client client = new Client(id, name, age, email);
//                return client;
//            }
//        }catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return ;
//    }
    public void deletePerson(Person person) {
        String sql = "delete from person where id = ?";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setLong(1, getIdOfPerson(person));
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

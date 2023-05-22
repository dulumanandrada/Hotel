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

public class ClientRepository {
    public void createClient(Client client) {
        PersonRepository personRepository = new PersonRepository();
        Person person = (Person) client;
        personRepository.createPerson(person);
        String sql = "insert into client values (?, ?) ";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setLong(1, personRepository.getIdOfPerson(person));
            statement.setString(2, client.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Client> readAllClients() {
        List<Client> clientList = new ArrayList<>();
        String sql = "select * from client c, person p where c.id = p.id";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                long id = result.getLong("id");
                String name = result.getString("name");
                int age = result.getInt("age");
                String email = result.getString("email");
                Client client = new Client(id, name, age, email);
                clientList.add(client);
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return clientList;
    }
    public Client getClientById(long ID) {
        String sql = "select * from client c, person p where c.id = p.id and c.id = ?";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setLong(1, ID);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                long id = result.getLong("id");
                String name = result.getString("name");
                int age = result.getInt("age");
                String email = result.getString("email");
                Client client = new Client(id, name, age, email);
                return client;
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new Client();
    }
    public void deleteClientById(long id) {
        String sql = "delete from client where id = ?";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteClientAll() {
        String sql = "delete from client";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

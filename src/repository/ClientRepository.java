package repository;

import config.DatabaseConnection;
import model.Client;
import model.Person;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClientRepository {
    public void createClient(Client client) {
        PersonRepository personRepository = new PersonRepository();
        Person person = (Person) client;
        personRepository.createPerson(person);
        String sql = "insert into client values (?, ?) ";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setInt(1, personRepository.getIdOfPerson(person));
            statement.setString(2, client.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

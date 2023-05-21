package repository;

import config.DatabaseConnection;
import model.Bank;
import model.Person;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BankRepository {
    public void addBank(Bank bank) {
        String sql = "insert into bank values (null, ?, ?) ";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setString(1, bank.getName());
            statement.setInt(2, bank.getAge());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

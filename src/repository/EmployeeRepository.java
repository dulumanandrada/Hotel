package repository;

import config.DatabaseConnection;
import model.Employee;
import model.Person;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {
    public void createEmployee(Employee employee) {
        PersonRepository personRepository = new PersonRepository();
        Person person = (Person) employee;
        personRepository.createPerson(person);
        String sql = "insert into employee values (?, ?)";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.setLong(1, personRepository.getIdOfPerson(person));
            statement.setDouble(2, employee.getSalary());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Employee> readAllEmployees() {
        List<Employee> employeeList = new ArrayList<>();
        String sql = "select * from employee e, person p where e.id = p.id";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                long id = result.getLong("id");
                String name = result.getString("name");
                int age = result.getInt("age");
                double salary = result.getDouble("salary");
                Employee employee = new Employee(id, name, age, salary);
                employeeList.add(employee);
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return employeeList;
    }
    public void updateEmployee(long id, Employee employee) {
        PersonRepository personRepository = new PersonRepository();
        String sql = "update employee set salary = ? where id = ?";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            personRepository.updatePerson(id, (Person) employee);
            statement.setDouble(1, employee.getSalary());
            statement.setLong(2, id);
            statement.executeUpdate();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteEmployeeById(long id) {
        PersonRepository personRepository = new PersonRepository();
        personRepository.deletePersonById(id);
    }
    public void deleteEmployeeAll() {
        String sql = "delete from employee";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(sql)) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

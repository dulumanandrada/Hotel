package service;

import model.Employee;
import repository.EmployeeRepository;

import java.util.List;

public class EmployeeService {
    private EmployeeRepository employeeRepository;
    public EmployeeService() {
        this.employeeRepository = new EmployeeRepository();
    }
    public Employee build(String clientDetails) {
        String[] attributes = clientDetails.split("/");
        String name = attributes[0];
        int age = Integer.valueOf(attributes[1]);
        double salary = Double.valueOf(attributes[2]);
        return new Employee(name, age, salary);
    }
    public void printEmployees(List<Employee> employees) {
        for(Employee e : employees)
            System.out.println(e);
    }

    public void createEmployee(Employee employee) {
        employeeRepository.createEmployee(employee);
    }

    public void deleteEmployee(Employee employee) {
        employeeRepository.deleteEmployee(employee);
    }
    public void deleteEmployeeById(long id) {
        employeeRepository.deleteEmployeeById(id);
    }

    public List<Employee> readAllEmployees() {
        return employeeRepository.readAllEmployees();
    }
}

package singleton;

import model.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeSingleton {
    private static EmployeeSingleton single_instance = null;
    private List<Employee> employees = new ArrayList<>();
    public static EmployeeSingleton getInstance() {
        if(single_instance == null)
            single_instance = new EmployeeSingleton();
        return single_instance;
    }
    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
    public List<Employee> getEmployees() {
        return employees;
    }
    private static List<String[]> getCSVColumns(String fileName) {
        List<String[]> columns = new ArrayList<>();
        try(var in = new BufferedReader(new FileReader(fileName))) {
            String line;
            while((line = in.readLine()) != null) {
                String[] fields = line.replaceAll(" ", "").split(",");
                columns.add(fields);
            }
        }catch (IOException e) {
            System.out.println("No saved employees!");
        }
        return columns;
    }
    public void loadFromCSV() {
        try{
            var columns = EmployeeSingleton.getCSVColumns("/Users/andradaduluman/IdeaProjects/Hotel/src/data/employee.csv");
            for(var fields : columns) {
                var newemployee = new Employee(fields[0], Integer.parseInt(fields[1]), Double.parseDouble(fields[2]));
                employees.add(newemployee);
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void dumpToCSV() {
        try{
            var writer = new FileWriter("/Users/andradaduluman/IdeaProjects/Hotel/src/data/employee.csv");
            for(var employee : employees){
                writer.write(employee.toCSV());
                writer.write("\n");
            }
            writer.close();
        }catch (IOException e){
            System.out.println(e.toString());
        }
    }
}

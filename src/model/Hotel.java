package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Hotel {
    private String Name;
    private Client[] clients = new Client[101];
    //private Employee[] employees = new Employee[101];
    private Set<Employee> employees = new HashSet<Employee>();
    private List<Room> rooms = new ArrayList<>();
    private int numOfClients = 0;
    private int numOfRooms = 0;

    public Hotel() { }

    public void setNumOfClients() {
        this.numOfClients ++;
    }
    public Client[] getClients() {
        return clients;
    }
    public HashSet<Employee> getEmployees() {
        return (HashSet<Employee>) employees;
    }
    public int getNumOfClients() {
        return numOfClients;
    }
    public int getNumOfRooms() {
        return numOfRooms;
    }
    public void setNumOfRooms() {
        this.numOfRooms ++;
    }
    public List<Room> getRooms() {
        return rooms;
    }
}

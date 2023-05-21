package model;

import java.util.*;

public class Hotel {
    private String Name;
    //private Client[] clients = new Client[101];
    private List<Client> clients = new ArrayList<>();

    //private Set<Employee> employees = new HashSet<Employee>();
    private HashMap<Integer, Employee> employees = new HashMap<>();
    int numOfEmployees = 0;
    private List<Room> rooms = new ArrayList<>();
    private int numOfClients = 0;
    private int numOfRooms = 0;

    public Hotel() { }

    public void setNumOfEmployees() {
        this.numOfEmployees ++;
    }
    public List<Client> getClients() {
        return clients;
    }
    public HashMap<Integer, Employee> getEmployees() {
        return (HashMap<Integer, Employee>) employees;
    }
    public int getNumOfEmployees() {
        return numOfEmployees;
    }
    public int getNumOfRooms() {
        return numOfRooms++;
    }
    public void setNumOfRooms() {
        this.numOfRooms ++;
    }
    public List<Room> getRooms() {
        return rooms;
    }
}



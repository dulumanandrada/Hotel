package model;

public class Hotel {
    private String Name;
    private Client[] clients = new Client[101];
    private Employee[] employees = new Employee[101];
    //private Room[] rooms = new Room[101];
    private int numOfClients = 0;
    private int numOfEmployees = 0;
    //private int numOfRooms = 0;

    public Hotel() { }

    public void setName(String name) {
        Name = name;
    }
    public void setNumOfClients(int numOfClients) {
        this.numOfClients = numOfClients;
    }
    public void setNumOfEmployees(int numOfEmployees) {
        this.numOfEmployees = numOfEmployees;
    }
    public String getName() {
        return Name;
    }
    public Client[] getClients() {
        return clients;
    }
    public Employee[] getEmployees() {
        return employees;
    }
    public int getNumOfClients() {
        return numOfClients;
    }
    public int getNumOfEmployees() {
        return numOfEmployees;
    }
}

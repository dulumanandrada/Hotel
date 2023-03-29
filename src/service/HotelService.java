package service;
import model.*;

public class HotelService {
    public void addRoom(Hotel hotel, Room room) {
        int nrofrooms = hotel.getNumOfRooms();
        hotel.setNumOfRooms();
        hotel.getRooms()[nrofrooms] = room;
        System.out.println("The room has been added.");
    }

    public void addEmployee(Hotel hotel, Employee employee) {
        int nrofemployees = hotel.getNumOfEmployees();
        hotel.setNumOfEmployees();
        hotel.getEmployees()[nrofemployees] = employee;
        System.out.println("The employee has been added.");
    }

    public void listEmployees(Hotel hotel) {
        for(Person p : hotel.getEmployees()) {
            if(p != null)
                System.out.println((Employee) p);
        }
    }

    public void addClient(Hotel hotel, Client client) {
        int nrofclients = hotel.getNumOfClients();
        hotel.setNumOfClients();
        hotel.getClients()[nrofclients] = client;
        System.out.println("The client has been added.");
    }

    public void listClients(Hotel hotel) {
        for(Person p : hotel.getClients()) {
            if(p != null)
                System.out.println((Client) p);
        }
    }

    public void addBooking(Hotel hotel, int numberRoom, Person person1) {
        for(Room r : hotel.getRooms()) {
            if(r != null && r.getNumber() == numberRoom)
            {
                ((SingleRoom) r).setPerson1(person1);
                r.setAvailability(false);
            }
        }
        System.out.println("The booking has been made.");
    }

    public void addBooking(Hotel hotel, int numberRoom, Person person1, Person person2) {
        for(Room r : hotel.getRooms()) {
            if(r != null && r.getNumber() == numberRoom)
            {
                ((DoubleRoom) r).setPerson1(person1);
                ((DoubleRoom) r).setPerson2(person2);
                r.setAvailability(false);
            }
        }
        System.out.println("The booking has been added.");
    }

    public void deleteBooking(Hotel hotel, int numberRoom) {
        for(Room r : hotel.getRooms()) {
            if(r != null && r.getNumber() == numberRoom)
            {
                r.setAvailability(true);
            }
        }
        System.out.println("The booking has been deleted.");
    }

    public void listAvailableRooms(Hotel hotel, String type) {
        if(hotel.getRooms() != null) {
            int nr = hotel.getNumOfRooms();
            int nrAvailable = 0;

            if(type == "single") {
                for (Room r : hotel.getRooms())
                    if (r != null && !(r instanceof DoubleRoom) && r.getAvailability() == true) {
                        System.out.println(r);
                        nrAvailable++;
                    }
                if (nrAvailable == 0)
                    System.out.println("No Single Room is available for the moment.");
            }
            if(type == "double") {
                for (Room r : hotel.getRooms())
                    if (r != null && r instanceof DoubleRoom && r.getAvailability() == true) {
                        System.out.println(r);
                        nrAvailable++;
                    }
                if (nrAvailable == 0)
                    System.out.println("No Double Room is available for the moment.");
            }
            if(type == "all") {
                for(Room r : hotel.getRooms())
                    if (r != null &&  r.getAvailability() == true) {
                        System.out.println(r);
                        nrAvailable++;
                    }
                if (nrAvailable == 0)
                    System.out.println("No Room is available for the moment.");
            }
        }
        else
            System.out.println("No rooms.");
    }

    public void listRooms(Hotel hotel) {
        for(Room r : hotel.getRooms())
            if(r != null)
                System.out.println(r);
    }
}

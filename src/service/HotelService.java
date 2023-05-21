package service;
import exception.TooManyProductsException;
import model.*;

import java.util.*;

public class HotelService {
    public void addRoom(Hotel hotel, Room room) {
        hotel.setNumOfRooms();
        hotel.getRooms().add(room);
        System.out.println("The room has been added.");
    }

    public void addEmployee(Hotel hotel, String[] details) {
        //hotel.getEmployees().add(employee);
        try{
            Person person = new Employee(details[0], Integer.valueOf(details[1]), Double.valueOf(details[2]));
            hotel.getEmployees().put(hotel.getNumOfEmployees(), (Employee) person);
            hotel.setNumOfEmployees();
            System.out.println("The employee has been added.");
        }catch(NumberFormatException e) {
            throw new TooManyProductsException("banana");
        }
    }

    public void listEmployees(Hotel hotel) {
        if(hotel.getEmployees().isEmpty())
            System.out.println("There is no employee working at the hotel.");
        else {
            for (Employee e : hotel.getEmployees().values()) {
                System.out.println(e);
            }
        }
    }

    public void sortEmployee(Hotel hotel) {
        List<Person> person = new ArrayList<>();
        for(Employee p : hotel.getEmployees().values())
            if(p != null)
                person.add(p);
        if(person.isEmpty())
            System.out.println("There is no employee working at the hotel.");
        else {
            Collections.sort(person, new EmployeeComparator());
            System.out.println("These are the employees sorted by salary: ");
            for(Person p : person)
                System.out.println(p);
        }
    }

    public void addClient(Hotel hotel, Client client) {
        hotel.getClients().add(client);
        System.out.println("The client has been added.");
    }

    public void listClients(Hotel hotel) {
        int nr = 0;
            for(Person p : hotel.getClients()) {
                if(p != null)
                {
                    System.out.print((Client) p);
                    nr ++;
                }
            }
        if(nr == 0)
            System.out.println("There is no client at the hotel.");
    }

    public void addBooking(Hotel hotel, int numberRoom, Person person1) {
        boolean ok = false;
        for(Room r : hotel.getRooms()) {
            if(r != null && r.getNumber() == numberRoom && r.getAvailability() == true)
            {
                ((SingleRoom) r).setPerson1(person1);
                r.setAvailability(false);
                ok = true;
            }
        }
        if(ok == true)
            System.out.println("The booking has been made.");
        else System.out.println("There is no room with this number to do check in for.");
    }

    public void addBooking(Hotel hotel, int numberRoom, Person person1, Person person2) {
        boolean ok = false;
        for(Room r : hotel.getRooms()) {
            if(r != null && r.getNumber() == numberRoom && r.getAvailability() == true)
            {
                ((DoubleRoom) r).setPerson1(person1);
                ((DoubleRoom) r).setPerson2(person2);
                r.setAvailability(false);
                ok = true;
            }
        }
        if(ok == true)
            System.out.println("The booking has been added.");
        else System.out.println("There is no room with this number to do check in for.");
    }

    public void deleteBooking(Hotel hotel, int numberRoom) {
        boolean found = false;
        for(Room r : hotel.getRooms()) {
            if(r != null && r.getNumber() == numberRoom && r.getAvailability() == false)
            {
                Client c = (Client)((SingleRoom) r).getPerson1();
                deleteClient(hotel, c);
                ((SingleRoom) r).setPerson1(new Client());
                if(r instanceof DoubleRoom) {
                    Client c1 = (Client)((DoubleRoom) r).getPerson2();
                    deleteClient(hotel, c1);
                    ((DoubleRoom) r).setPerson2(new Client());
                }
                r.setAvailability(true);
                found = true;
            }
        }
        if(found == true)
            System.out.println("The booking has been deleted.");
        else
            System.out.println("There is no room with this number to do check out for.");
    }

    public void deleteClient(Hotel hotel, Client client) {
        hotel.getClients().remove(client);
        System.out.println("The client has been deleted");
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

    public void listUnavailableRooms(Hotel hotel) {
        int nr = 0;
        for(Room r : hotel.getRooms())
            if(r != null && r.getAvailability() == false)
            {
                System.out.println(r);
                nr ++;
            }
        if(nr == 0)
            System.out.println("There is no unavailable room.");
    }

    public void listRooms(Hotel hotel) {
        if(hotel.getRooms().isEmpty())
            System.out.println("There is no room at the hotel.");
        else {
            for(Room r : hotel.getRooms())
                if(r != null)
                    System.out.println(r);
        }
    }

    public void howManyRooms(Hotel hotel) {
        int nrS = 0, nrD = 0;
        int nrSfree = 0, nrDfree = 0;
        for(Room r : hotel.getRooms())
            if(r != null && r instanceof DoubleRoom)
            {
                nrD ++;
                if(r.getAvailability() == true)
                    nrDfree ++;
            }
            else if(r != null && r instanceof SingleRoom)
            {
                nrS ++;
                if(r.getAvailability() == true)
                    nrSfree ++;
            }
        System.out.println("There are " + nrS + " single rooms (" + nrSfree + " vailable) "+ "and "
                + nrD + " double rooms (" + nrDfree + " vailable).");
    }

    public void sortRoomsDesc(Hotel hotel) {
        Collections.sort(hotel.getRooms(), Collections.reverseOrder(new RoomComparator()));
    }
    public void sortRoomsAsc(Hotel hotel) {
        Collections.sort(hotel.getRooms(), new RoomComparator());
    }
}

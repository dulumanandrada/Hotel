package service;
import exception.TooManyProductsException;
import model.*;
import repository.PersonRepository;
import repository.RoomRepository;
import singleton.ClientSingleton;
import singleton.DoubleRoomSingleton;
import singleton.EmployeeSingleton;
import singleton.SingleRoomSingleton;

import java.util.*;

public class HotelService {
    private final Hotel hotel;
    private List<Client> clients = new ArrayList<>();
    private List<Employee> employees = new ArrayList<>();
    private List<SingleRoom> singleRooms = new ArrayList<>();
    private List<DoubleRoom> doubleRooms = new ArrayList<>();
    private final EmployeeService employeeService;
    private final ClientService clientService;
    private final SingleRoomService singleRoomService;
    private final DoubleRoomService doubleRoomService;
    public HotelService() {
        this.hotel = new Hotel();
        this.employeeService = new EmployeeService();
        this.clientService = new ClientService();
        this.singleRoomService = new SingleRoomService();
        this.doubleRoomService = new DoubleRoomService();
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
    public void setSingleRooms(List<SingleRoom> singleRooms) {
        this.singleRooms = singleRooms;
    }
    public void setDoubleRooms(List<DoubleRoom> doubleRooms) {
        this.doubleRooms = doubleRooms;
    }
    public void deleteAllDatabase() {
        PersonRepository personRepository = new PersonRepository();
        personRepository.deletePersonAll();
        RoomRepository roomRepository = new RoomRepository();
        roomRepository.deleteRoomAll();
    }
    public void seedData() {
        ClientSingleton.getInstance().loadFromCSV();
        this.setClients(ClientSingleton.getInstance().getClients());
        for(Client c : clients)
            clientService.createClient(c);

        EmployeeSingleton.getInstance().loadFromCSV();
        this.setEmployees(EmployeeSingleton.getInstance().getEmployees());
        for(Employee e : employees)
            employeeService.createEmployee(e);

        SingleRoomSingleton.getInstance().loadFromCSV();
        this.setSingleRooms(SingleRoomSingleton.getInstance().getSingleRooms());
        for(SingleRoom s : singleRooms)
            singleRoomService.createSingleRoom(s);

        DoubleRoomSingleton.getInstance().loadFromCSV();
        this.setDoubleRooms(DoubleRoomSingleton.getInstance().getDoubleRooms());
        for(DoubleRoom d : doubleRooms)
            doubleRoomService.createDoubleRoom(d);
    }
    public void exportData() {
        ClientSingleton.getInstance().setClients(clientService.readAllClients());
        ClientSingleton.getInstance().dumpToCSV();

        EmployeeSingleton.getInstance().setEmployees(employeeService.readAllEmployees());
        EmployeeSingleton.getInstance().dumpToCSV();

        SingleRoomSingleton.getInstance().setSingleRooms(singleRoomService.readAllSingleRooms());
        SingleRoomSingleton.getInstance().dumpToCSV();

        DoubleRoomSingleton.getInstance().setDoubleRooms(doubleRoomService.readAllDoubleRooms());
        DoubleRoomSingleton.getInstance().dumpToCSV();
    }
    public void addSingleRoom(String details) {
        try{
            SingleRoom singleRoom = singleRoomService.build(details);
            singleRoomService.createSingleRoom(singleRoom);
            System.out.println("The single room has been added.");
        }catch(NumberFormatException e) {
            throw new TooManyProductsException("banana");
        }
    }
    public void deleteSingleRoomById(String details) {
        try{
            long id = Long.valueOf(details);
            singleRoomService.deleteSingleRoomById(id);
            System.out.println("The single room has been deleted.");
        }catch(NumberFormatException e) {
            throw new TooManyProductsException("Invalid number for ID.");
        }
    }
    public void listSingleRooms() {
        if(singleRoomService.readAllSingleRooms().isEmpty())
            System.out.println("There is no single room at the hotel.");
        else {
            singleRoomService.printSingleRooms(singleRoomService.readAllSingleRooms());
        }
    }
    public void addDoubleRoom(String details) {
        try{
            DoubleRoom doubleRoom = doubleRoomService.build(details);
            doubleRoomService.createDoubleRoom(doubleRoom);
            System.out.println("The double room has been added.");
        }catch(NumberFormatException e) {
            throw new TooManyProductsException("banana");
        }
    }
    public void deleteDoubleRoomById(String details) {
        try{
            long id = Long.valueOf(details);
            doubleRoomService.deleteDoubleRoomById(id);
            System.out.println("The double room has been deleted.");
        }catch(NumberFormatException e) {
            throw new TooManyProductsException("Invalid number for ID.");
        }
    }
    public void listDoubleRooms() {
        if(doubleRoomService.readAllDoubleRooms().isEmpty())
            System.out.println("There is no double room at the hotel.");
        else {
            doubleRoomService.printDoubleRooms(doubleRoomService.readAllDoubleRooms());
        }
    }

    public void addEmployee(String details) {
        try{
            Employee employee = employeeService.build(details);
            employeeService.createEmployee(employee);
            System.out.println("The employee has been added.");
        }catch(NumberFormatException e) {
            throw new TooManyProductsException("banana");
        }
    }

    public void deleteEmployeeById(String details) {
        try{
            long id = Long.valueOf(details);
            employeeService.deleteEmployeeById(id);
            System.out.println("The employee has been deleted.");
        }catch(NumberFormatException e) {
            throw new TooManyProductsException("Invalid number for ID.");
        }
    }
    public void listEmployees() {
        if(employeeService.readAllEmployees().isEmpty())
            System.out.println("There is no employee working at the hotel.");
        else {
            employeeService.printEmployees(employeeService.readAllEmployees());
        }
    }
    public void sortEmployee() {
        List<Employee> employees = employeeService.readAllEmployees();
        if(employees.isEmpty())
            System.out.println("There is no employee working at the hotel.");
        else {
            Collections.sort(employees, new EmployeeComparator());
            System.out.println("These are the employees sorted by salary: ");
            employeeService.printEmployees(employees);
        }
    }
    public void addClient(String details) {
        try{
            Client client = clientService.build(details);
            clientService.createClient(client);
            System.out.println("The client has been added.");
        }catch(NumberFormatException e) {
            throw new TooManyProductsException("banana");
        }
    }

    public void listClients() {
            if(clientService.readAllClients().isEmpty())
                System.out.println("There is no client at the hotel.");
            else {
                clientService.printClients(clientService.readAllClients());
            }
    }

//    public void addBooking(Hotel hotel, int numberRoom, Person person1) {
//        boolean ok = false;
//        for(Room r : hotel.getRooms()) {
//            if(r != null && r.getNumber() == numberRoom && r.getAvailability() == true)
//            {
//                ((SingleRoom) r).setPerson1(person1);
//                r.setAvailability(false);
//                ok = true;
//            }
//        }
//        if(ok == true)
//            System.out.println("The booking has been made.");
//        else System.out.println("There is no room with this number to do check in for.");
//    }
//
//    public void addBooking(Hotel hotel, int numberRoom, Person person1, Person person2) {
//        boolean ok = false;
//        for(Room r : hotel.getRooms()) {
//            if(r != null && r.getNumber() == numberRoom && r.getAvailability() == true)
//            {
//                ((DoubleRoom) r).setPerson1(person1);
//                ((DoubleRoom) r).setPerson2(person2);
//                r.setAvailability(false);
//                ok = true;
//            }
//        }
//        if(ok == true)
//            System.out.println("The booking has been added.");
//        else System.out.println("There is no room with this number to do check in for.");
//    }

//    public void deleteBooking(Hotel hotel, int numberRoom) {
//        boolean found = false;
//        for(Room r : hotel.getRooms()) {
//            if(r != null && r.getNumber() == numberRoom && r.getAvailability() == false)
//            {
//                Client c = (Client)((SingleRoom) r).getPerson1();
//                deleteClient(hotel, c);
//                ((SingleRoom) r).setPerson1(new Client());
//                if(r instanceof DoubleRoom) {
//                    Client c1 = (Client)((DoubleRoom) r).getPerson2();
//                    deleteClient(hotel, c1);
//                    ((DoubleRoom) r).setPerson2(new Client());
//                }
//                r.setAvailability(true);
//                found = true;
//            }
//        }
//        if(found == true)
//            System.out.println("The booking has been deleted.");
//        else
//            System.out.println("There is no room with this number to do check out for.");
//    }

    public void deleteClientById(String details) {
        try{
            long id = Long.valueOf(details);
            clientService.deleteClientById(id);
            System.out.println("The client has been deleted.");
        }catch(NumberFormatException e) {
            throw new TooManyProductsException("Invalid number for ID.");
        }
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

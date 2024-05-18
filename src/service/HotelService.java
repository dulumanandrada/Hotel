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
    private List<Client> clients = new ArrayList<>();
    private List<Employee> employees = new ArrayList<>();
    private List<SingleRoom> singleRooms = new ArrayList<>();
    private List<DoubleRoom> doubleRooms = new ArrayList<>();
    private final EmployeeService employeeService;
    private final ClientService clientService;
    private final SingleRoomService singleRoomService;
    private final DoubleRoomService doubleRoomService;
    public HotelService() {
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

    // -----------------------------------> DATA <-----------------------------------
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

    // -----------------------------------> SINGLEROOM <-----------------------------------
    public void addSingleRoom(String details) {
        try{
            SingleRoom singleRoom = singleRoomService.build(details);
            singleRoomService.createSingleRoom(singleRoom);
            System.out.println("The single room has been added.");
        }catch(NumberFormatException e) {
            throw new TooManyProductsException("Invalid input. Try int/double");
        }catch (ArrayIndexOutOfBoundsException e) {
            throw new TooManyProductsException("Invalid input. Too many or not enough attributes.");
        }
    }
    public void deleteSingleRoomById(String details) {
        try{
            long id = Long.valueOf(details);
            singleRoomService.deleteSingleRoomById(id);
            System.out.println("The single room has been deleted.");
        }catch(NumberFormatException e) {
            throw new TooManyProductsException("Invalid number for ID. Try long type.");
        }catch (ArrayIndexOutOfBoundsException e) {
            throw new TooManyProductsException("Invalid input. Too many or not enough attributes.");
        }
    }
    public void updateSingleRoomPrice(Scanner scanner) {
        System.out.println("Insert the ID of the room you want to update.");
        listSingleRooms();
        try {
            long id = Long.valueOf(scanner.nextLine());
            System.out.println("Insert the new price.");
            double newPrice = Double.valueOf(scanner.nextLine());
            singleRoomService.updateSingleRoomPrice(id, newPrice);
        }catch (Exception e) {
           e.printStackTrace();
        }
    }
    public void listSingleRooms() {
        if(singleRoomService.readAllSingleRooms().isEmpty())
            System.out.println("There is no single room at the hotel.");
        else {
            singleRoomService.printSingleRooms(singleRoomService.readAllSingleRooms());
        }
    }

    // -----------------------------------> DOUBLEROOM <-----------------------------------
    public void addDoubleRoom(String details) {
        try{
            DoubleRoom doubleRoom = doubleRoomService.build(details);
            doubleRoomService.createDoubleRoom(doubleRoom);
            System.out.println("The double room has been added.");
        }catch(NumberFormatException e) {
            throw new TooManyProductsException("Invalid input. Try int/double");
        }catch (ArrayIndexOutOfBoundsException e) {
            throw new TooManyProductsException("Invalid input. Too many or not enough attributes.");
        }
    }
    public void deleteDoubleRoomById(String details) {
        try{
            long id = Long.valueOf(details);
            doubleRoomService.deleteDoubleRoomById(id);
            System.out.println("The double room has been deleted.");
        }catch(NumberFormatException e) {
            throw new TooManyProductsException("Invalid number for ID. Try long type.");
        }catch (ArrayIndexOutOfBoundsException e) {
            throw new TooManyProductsException("Invalid input. Too many or not enough attributes.");
        }
    }
    public void listDoubleRooms() {
        if(doubleRoomService.readAllDoubleRooms().isEmpty())
            System.out.println("There is no double room at the hotel.");
        else {
            doubleRoomService.printDoubleRooms(doubleRoomService.readAllDoubleRooms());
        }
    }
    public void updateDoubleRoomPrice(Scanner scanner) {
        System.out.println("Insert the ID of the room you want to update.");
        listDoubleRooms();
        try {
            long id = Long.valueOf(scanner.nextLine());
            System.out.println("Insert the new price.");
            double newPrice = Double.valueOf(scanner.nextLine());
            doubleRoomService.updateDoubleRoomPrice(id, newPrice);
        }catch(NumberFormatException e) {
            throw new TooManyProductsException("Invalid input.");
        }catch (ArrayIndexOutOfBoundsException e) {
            throw new TooManyProductsException("Too many or not enough attributes as input.");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    // -----------------------------------> EMPLOYEE <-----------------------------------
    public void addEmployee(String details) {
        try{
            Employee employee = employeeService.build(details);
            employeeService.createEmployee(employee);
            System.out.println("The employee has been added.");
        }catch(NumberFormatException e) {
            throw new TooManyProductsException("The input is not valid. Try this format <string/int/double>.");
        }catch (ArrayIndexOutOfBoundsException e) {
            throw new TooManyProductsException("Not enough attributes as input. The employee was not added.");
        }
    }
    public void updateEmployee(Scanner scanner) {
        System.out.println("Insert the Id of the employee you want to update.");
        listEmployees();
        try {
            long id = Long.valueOf(scanner.nextLine());
            System.out.println("Insert new details: name/age/salary");
            Employee employee = employeeService.build(scanner.nextLine());
            employeeService.updateEmployee(id, employee);
        }catch(NumberFormatException e) {
            throw new TooManyProductsException("Invalid input.");
        }catch (ArrayIndexOutOfBoundsException e) {
            throw new TooManyProductsException("Too many or not enough attributes as input.");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteEmployeeById(String details) {
        try{
            long id = Long.valueOf(details);
            employeeService.deleteEmployeeById(id);
            System.out.println("The employee has been deleted.");
        }catch(NumberFormatException e) {
            throw new TooManyProductsException("Invalid number for ID. The employee was not deleted.");
        }catch (ArrayIndexOutOfBoundsException e) {
            throw new TooManyProductsException("Not enough attributes as input. The employee was not deleted.");
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

    // -----------------------------------> CLIENT <-----------------------------------
    public void addClient(String details) {
        try{
            Client client = clientService.build(details);
            clientService.createClient(client);
            System.out.println("The client has been added.");
        }catch(NumberFormatException e) {
            throw new TooManyProductsException("The input is not valid. Try this format <string/int/double>.");
        }catch (ArrayIndexOutOfBoundsException e) {
            throw new TooManyProductsException("Not enough attributes as input. The employee was not added.");
        }
    }
    public void addClient(Client client) {
        try{
            clientService.createClient(client);
            System.out.println("The client has been added.");
        }catch(NumberFormatException e) {
            throw new TooManyProductsException("The input is not valid. Try this format <string/int/double>.");
        }catch (ArrayIndexOutOfBoundsException e) {
            throw new TooManyProductsException("Not enough attributes as input. The employee was not added.");
        }
    }
    public void updateClient(Scanner scanner) {
        System.out.println("Insert the ID of the client you want to update.");
        listClients();
        try {
            long id = Long.valueOf(scanner.nextLine());
            System.out.println("Insert new details: name/age/email");
            Client client = clientService.build(scanner.nextLine());
            clientService.updateClient(id, client);
        }catch(NumberFormatException e) {
            throw new TooManyProductsException("Invalid input.");
        }catch (ArrayIndexOutOfBoundsException e) {
            throw new TooManyProductsException("Too many or not enough attributes as input.");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void listClients() {
        if(clientService.readAllClients().isEmpty())
            System.out.println("There is no client at the hotel.");
        else {
            clientService.printClients(clientService.readAllClients());
        }
    }
    public void deleteClientById(String details) {
        try{
            long id = Long.valueOf(details);
            clientService.deleteClientById(id);
            System.out.println("The client has been deleted.");
        }catch(NumberFormatException e) {
            throw new TooManyProductsException("Invalid input for ID.");
        }catch (ArrayIndexOutOfBoundsException e) {
            throw new TooManyProductsException("Too many or not enough attributes as input.");
        }
    }

    // -----------------------------------> ALL ROOMS <-----------------------------------
    public void listAURooms(boolean ok) {
        singleRooms = singleRoomService.readAUSingleRooms(ok);
        doubleRooms = doubleRoomService.readAUDoubleRooms(ok);
        List<Room> allRooms = new ArrayList<>();
        allRooms.addAll(singleRooms);
        allRooms.addAll(doubleRooms);
        if(allRooms.isEmpty())
            System.out.println("No rooms.");
        else {
            for (var r : allRooms)
                System.out.println(r);
        }
    }
    public void listAUSingleRooms(boolean ok) {
        singleRooms = singleRoomService.readAUSingleRooms(ok);
        if(singleRooms.isEmpty())
            System.out.println("No rooms.");
        else {
            for(var r : singleRooms)
                System.out.println(r);
        }

    }
    public void listAUDoubleRooms(boolean ok) {
        doubleRooms = doubleRoomService.readAUDoubleRooms(ok);
        if(doubleRooms.isEmpty())
            System.out.println("No rooms.");
        else {
            for(var r : doubleRooms)
                System.out.println(r);
        }
    }
    public void howManyRooms() {
        singleRooms = singleRoomService.readAllSingleRooms();
        int nrS = singleRooms.size();
        doubleRooms = doubleRoomService.readAllDoubleRooms();
        int nrD = doubleRooms.size();
        System.out.println("There are " + nrS + " single rooms and "
                + nrD + " double rooms.");
    }

    public List<Room> concatAllRooms() {
        singleRooms = singleRoomService.readAllSingleRooms();
        doubleRooms = doubleRoomService.readAllDoubleRooms();
        List<Room> allRooms = new ArrayList<>();
        allRooms.addAll(singleRooms);
        allRooms.addAll(doubleRooms);
        return allRooms;
    }
    public void sortRoomsDesc() {
        List<Room> allRooms = concatAllRooms();
        Collections.sort(allRooms, Collections.reverseOrder(new RoomComparator()));
        if(allRooms.isEmpty())
            System.out.println("No rooms.");
        else {
            for (var r : allRooms)
                System.out.println(r);
        }
    }
    public void sortRoomsAsc() {
        List<Room> allRooms = concatAllRooms();
        Collections.sort(allRooms, new RoomComparator());
        if(allRooms.isEmpty())
            System.out.println("No rooms.");
        else {
            for (var r : allRooms)
                System.out.println(r);
        }
    }

    // -----------------------------------> BOOKING <-----------------------------------
    public void checkInSingle(Scanner scanner) {
        try {
            System.out.println("Choose the ID of wanted room.");
            listAUSingleRooms(true);
            long idRoom = Long.valueOf(scanner.nextLine());
            boolean ok = false;

            System.out.println("What's the person? name/age/email");
            Client newClient = clientService.build(scanner.nextLine());
            addClient(newClient);
            PersonRepository personRepository = new PersonRepository();
            long idPerson = personRepository.getIdOfPerson(newClient);

            singleRoomService.checkInSingleRoom(idRoom, idPerson);
        }catch(NumberFormatException e) {
            throw new TooManyProductsException("Invalid input.");
        }catch (ArrayIndexOutOfBoundsException e) {
            throw new TooManyProductsException("Too many or not enough attributes as input.");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkInDouble(Scanner scanner) {
        try {
            System.out.println("Choose the ID of wanted room.");
            listAUDoubleRooms(true);
            long idRoom = Long.valueOf(scanner.nextLine());

            System.out.println("What's the first person? name/age/email");
            Client newFirstClient = clientService.build(scanner.nextLine());
            addClient(newFirstClient);
            System.out.println("What's the second person? name/age/email");
            Client newSecondClient = clientService.build(scanner.nextLine());
            addClient(newSecondClient);
            PersonRepository personRepository = new PersonRepository();
            long idFirstPerson = personRepository.getIdOfPerson(newFirstClient);
            long idSecondPerson = personRepository.getIdOfPerson(newSecondClient);

            doubleRoomService.checkInDoubleRoom(idRoom, idFirstPerson, idSecondPerson);
        }catch(NumberFormatException e) {
            throw new TooManyProductsException("Invalid input.");
        }catch (ArrayIndexOutOfBoundsException e) {
            throw new TooManyProductsException("Too many or not enough attributes as input.");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void checkOutSingleRoom(Scanner scanner) {
        try {
            System.out.println("Choose the ID of room for which you want to do check out.");
            listAUSingleRooms(false);
            long idRoom = Long.valueOf(scanner.nextLine());
            singleRoomService.checkOutSingleRoom(idRoom);
        }catch(NumberFormatException e) {
            throw new TooManyProductsException("Invalid input for ID.");
        }catch (ArrayIndexOutOfBoundsException e) {
            throw new TooManyProductsException("Too many or not enough attributes as input.");
        }
    }
    public void checkOutDoubleRoom(Scanner scanner) {
        try {
            System.out.println("Choose the ID of room for which you want to do check out.");
            listAUDoubleRooms(false);
            long idRoom = Long.valueOf(scanner.nextLine());
            doubleRoomService.checkOutDoubleRoom(idRoom);
        }catch(NumberFormatException e) {
            throw new TooManyProductsException("Invalid input for ID.");
        }catch (ArrayIndexOutOfBoundsException e) {
            throw new TooManyProductsException("Too many or not enough attributes as input.");
        }
    }
}

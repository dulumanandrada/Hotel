package main;
import service.AuditService;
import service.HotelService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Meniu.printDescriere();
        HotelService hotelService = new HotelService();
        AuditService auditService = new AuditService();

        // --> delete everything is in database in order not to exist conflicts of duplicates
        // --> then seed with data from csv in database
        hotelService.deleteAllDatabase();
        hotelService.seedData();

        Scanner scanner = new Scanner(System.in);
        while(true) {
                Meniu.printNextCommand();
                handleTasks(hotelService,auditService, scanner);
        }
    }

    public static void handleTasks(HotelService hotelService, AuditService auditService, Scanner scanner) {
        String line = scanner.nextLine();
        try {
            switch (line) {
                case "add employee":
                    System.out.println("Please insert details of the employee. name/age/salary");
                    hotelService.addEmployee(scanner.nextLine());
                    break;
                case "delete employee":
                    System.out.println("PLease insert the ID of the employee you want to delete.");
                    hotelService.listEmployees();
                    hotelService.deleteEmployeeById(scanner.nextLine());
                    break;
                case "list employees":
                    hotelService.listEmployees();
                    break;
                case "sort employees":
                    hotelService.sortEmployee();
                    break;
                case "update employee":
                    hotelService.updateEmployee(scanner);
                    break;
                case "add client":
                    System.out.println("Please insert details of the employee. name/age/email");
                    hotelService.addClient(scanner.nextLine());
                    break;
                case "list clients":
                    hotelService.listClients();
                    break;
                case "delete client":
                    System.out.println("PLease insert the ID of the client you want to delete.");
                    hotelService.listClients();
                    hotelService.deleteClientById(scanner.nextLine());
                    break;
                case "update client":
                    hotelService.updateClient(scanner);
                    break;
                case "add room":
                    System.out.println("What kind of room: single/double ?");
                    System.out.println("Please insert details of the room. number/price");
                    String roomType1 = scanner.nextLine();
                    switch (roomType1) {
                        case "single": {
                            hotelService.addSingleRoom(scanner.nextLine());
                            break;
                        }
                        case "double": {
                            hotelService.addDoubleRoom(scanner.nextLine());
                            break;
                        }
                        default: System.out.println("This room type doesn't exist.");
                    }
                    break;
                case "delete room":
                    System.out.println("What kind of room: single/double ?");
                    String roomType2 = scanner.nextLine();
                    System.out.println("Insert the ID of the room you want to delete.");
                    switch (roomType2) {
                        case "single": {
                            hotelService.listSingleRooms();
                            hotelService.deleteSingleRoomById(scanner.nextLine());
                            break;
                        }
                        case "double": {
                            hotelService.listDoubleRooms();
                            hotelService.deleteDoubleRoomById(scanner.nextLine());
                            break;
                        }
                        default: System.out.println("This room type doesn't exist.");
                    }
                    break;
                case "update room price":
                    System.out.println("What kind of room: single/double ?");
                    String roomType3 = scanner.nextLine();
                    switch (roomType3) {
                        case "single": {
                            hotelService.updateSingleRoomPrice(scanner);
                            break;
                        }
                        case "double": {
                            hotelService.updateDoubleRoomPrice(scanner);
                            break;
                        }
                        default: System.out.println("This room type doesn't exist.");
                    }
                    break;
                case "list rooms":
                    System.out.println("What kind of room: single/double ?");
                    String roomType4 = scanner.nextLine();
                    switch (roomType4) {
                        case "single": {
                            hotelService.listSingleRooms();
                            break;
                        }
                        case "double": {
                            hotelService.listDoubleRooms();
                            break;
                        }
                        default: System.out.println("This room type doesn't exist.");
                    }
                    break;
                case "sort rooms":
                    System.out.println("What kind of sort: asc/desc ?");
                    String sortType = scanner.nextLine();
                    switch (sortType) {
                        case "asc": {
                            hotelService.sortRoomsAsc();
                            break;
                        }
                        case "desc": {
                            hotelService.sortRoomsDesc();
                            break;
                        }
                        default: System.out.println("This sort type doesn't exist.");
                    }
                    break;
                case "how many":
                    hotelService.howManyRooms();
                    break;
                case "list all rooms":
                    System.out.println("What kind of rooms available/unavailable: a/u ?");
                    String roomType5 = scanner.nextLine();
                    switch (roomType5) {
                        case "a": {
                            hotelService.listAURooms(true);
                            break;
                        }
                        case "u": {
                            hotelService.listAURooms(false);
                            break;
                        }
                        default: System.out.println("This room type doesn't exist.");
                    }
                    break;
                case "check in":
                    System.out.println("What kind of room: single/double ?");
                    String roomType6 = scanner.nextLine();
                    switch (roomType6) {
                        case "single": {
                            hotelService.checkInSingle(scanner);
                            break;
                        }
                        case "double": {
                            hotelService.checkInDouble(scanner);
                            break;
                        }
                        default: System.out.println("This room type doesn't exist.");
                    }
                    break;
                case "check out":
                    System.out.println("What kind of room: single/double ?");
                    String roomType7 = scanner.nextLine();
                    switch (roomType7) {
                        case "single": {
                            hotelService.checkOutSingleRoom(scanner);
                            break;
                        }
                        case "double": {
                            hotelService.checkOutDoubleRoom(scanner);
                            break;
                        }
                        default: System.out.println("This room type doesn't exist.");
                    }
                    break;
                case "exit":
                    Meniu.printGreeting();
                    hotelService.exportData();
                    System.exit(0);
                    break;
                default:
                    System.out.println("This command doesn't exist.");
            }
            auditService.logAction(line);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

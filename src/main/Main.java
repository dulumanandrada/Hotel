package main;
import exception.TooManyProductsException;
import model.*;
import service.HotelService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Meniu.printDescriere();
        Hotel hotel = new Hotel();
        HotelService hotelService = new HotelService();
        Scanner scanner = new Scanner(System.in);

        try {
            File myFile = new File("/Users/andradaduluman/IdeaProjects/Hotel/src/input.txt");
            Scanner myReader = new Scanner(myFile);
            while(myReader.hasNext()) {
                String data = myReader.nextLine();
                switch (data) {
                    case "add room":
                        String roomType2 = myReader.nextLine();
                        switch (roomType2) {
                            case "single": {
                                addSingleRoom(hotel, hotelService, myReader);
                                break;
                            }
                            case "double": {
                                addDoubleRoom(hotel, hotelService, myReader);
                                break;
                            }
                            default : System.out.println("This room type doesn't exist.");
                        }
                        break;
                    case "add employee":
                        addEmployee(hotel, hotelService, myReader);
                        break;
                    default : System.out.println("This command doesn't exist.");
                }
            }
            myReader.close();
        } catch(FileNotFoundException e) {
            System.out.println("An error has occurred.");
            e.printStackTrace();
        }

        while(true) {
                Meniu.printNextCommand();
                handleTasks(hotel, hotelService, scanner);
        }

    }

    public static void handleTasks(Hotel hotel, HotelService hotelService, Scanner scanner) {
        String line = scanner.nextLine();
        switch (line) {
            case "check in":
                System.out.println("What kind of room: single/double ?");
                String roomType1 = scanner.nextLine();
                switch (roomType1) {
                    case "single": {
                        checkInSingle(hotel, hotelService, scanner);
                        break;
                    }
                    case "double": {
                        checkInDouble(hotel, hotelService, scanner);
                        break;
                    }
                    default: System.out.println("This room type doesn't exist.");
                }
                break;
            case "check out":
                checkOut(hotel, hotelService, scanner);
                break;
            case "add room":
                System.out.println("What kind of room: single/double ?");
                String roomType2 = scanner.nextLine();
                switch (roomType2) {
                    case "single": {
                        System.out.println("PLease type the room details: number/price");
                        addSingleRoom(hotel, hotelService, scanner);
                        break;
                    }
                    case "double": {
                        System.out.println("PLease type the room details: number/price");
                        addDoubleRoom(hotel, hotelService, scanner);
                        break;
                    }
                    default : System.out.println("This room type doesn't exist.");
                }
                break;
            case "see free rooms":
                System.out.println("What kind of room: single/double/all ?");
                String roomType3 = scanner.nextLine();
                switch (roomType3) {
                    case "single": {
                        hotelService.listAvailableRooms(hotel, "single");
                        break;
                    }
                    case "double": {
                        hotelService.listAvailableRooms(hotel, "double");
                        break;
                    }
                    case "all": {
                        hotelService.listAvailableRooms(hotel, "all");
                        break;
                    }
                    default : System.out.println("This product type doesn't exist.");
                }
                break;
            case "see booked rooms":
                hotelService.listUnavailableRooms(hotel);
                break;
            case "see rooms":
                hotelService.listRooms(hotel);
                break;
            case "see clients":
                hotelService.listClients(hotel);
                break;
            case "add employee":
                System.out.println("Please insert details of the employee. name/age/salary");
                addEmployee(hotel, hotelService, scanner);
                break;
            case "delete employee":
                System.out.println("PLease insert the ID of the employee you want to remove.");
                for(int id : hotel.getEmployees().keySet()) {
                    System.out.println("ID: " + id + " " + hotel.getEmployees().get(id));
                }
                deleteEmployee(hotel, hotelService, scanner);
                break;
            case "see employees":
                hotelService.listEmployees(hotel);
                break;
            case "sort employees":
                hotelService.sortEmployee(hotel);
                break;
            case "how many":
                hotelService.howManyRooms(hotel);
                break;
            case "sort rooms asc":
                hotelService.sortRoomsAsc(hotel);
                hotelService.listRooms(hotel);
                break;
            case "sort rooms desc":
                hotelService.sortRoomsDesc(hotel);
                hotelService.listRooms(hotel);
                break;
            case "exit":
                Meniu.printGreeting();
                System.exit(0);
                break;
            default : System.out.println("This command doesn't exist.");
        }
    }

    public static void checkInSingle(Hotel hotel, HotelService hotelService, Scanner scanner){
        System.out.println("Choose the number of wanted room.");
        hotelService.listAvailableRooms(hotel, "single");
        int nrwanted = Integer.valueOf(scanner.nextLine());
        boolean ok = false;
        for(Room r : hotel.getRooms()) {
            if(r != null && r.getNumber() == nrwanted && r.getAvailability() == true)
                ok = true;
        }
        if(ok == false)
            System.out.println("There is no room with this number to do check in for.");
        else {
            System.out.println("What's the person? name/age/email");
            String[] details1 = scanner.nextLine().split("/");
            Person person1 = new Client(details1[0], Integer.valueOf(details1[1]), details1[2]);
            hotelService.addBooking(hotel, nrwanted, person1);
            hotelService.addClient(hotel, (Client) person1);
        }
    }

    public static void checkInDouble(Hotel hotel, HotelService hotelService, Scanner scanner) {
        System.out.println("Choose the number of wanted room.");
        hotelService.listAvailableRooms(hotel, "double");
        int nrwanted = Integer.valueOf(scanner.nextLine());
        boolean ok = false;
        for(Room r : hotel.getRooms()) {
            if(r != null && r.getNumber() == nrwanted && r.getAvailability() == true)
                ok = true;
        }
        if(ok == false)
            System.out.println("There is no room with this number to do check in for.");
        else {
            System.out.println("What's the first person? name/age/email");
            String[] details1 = scanner.nextLine().split("/");
            Person person1 = new Client(details1[0], Integer.valueOf(details1[1]), details1[2]);
            System.out.println("What's the second person? name/age/email");
            String[] details2 = scanner.nextLine().split("/");
            Person person2 = new Client(details2[0], Integer.valueOf(details2[1]), details2[2]);
            hotelService.addBooking(hotel, nrwanted, person1, person2);
            hotelService.addClient(hotel, (Client) person1);
            hotelService.addClient(hotel, (Client) person2);
        }
    }

    public static void checkOut(Hotel hotel, HotelService hotelService, Scanner scanner) {
        System.out.println("Choose a number of room for which you want to do check out.");
        hotelService.listUnavailableRooms(hotel);
        int nrwanted = Integer.valueOf(scanner.nextLine());
        hotelService.deleteBooking(hotel, nrwanted);
    }

    public static void addSingleRoom(Hotel hotel, HotelService hotelService, Scanner scanner) {
        try {
            String[] attributes = scanner.nextLine().split("/");
            Room newroom = new SingleRoom(Integer.valueOf(attributes[0]), Double.valueOf(attributes[1]));
            hotelService.addRoom(hotel, newroom);
        }catch (NumberFormatException e) {
            System.out.println("Invalid input for creation. The room was not added.");
        }catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Not enough attributes as input. The room was not added.");
        }catch (TooManyProductsException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addDoubleRoom(Hotel hotel, HotelService hotelService, Scanner scanner) {
        try {
            String[] attributes = scanner.nextLine().split("/");
            Room newroom = new DoubleRoom(Integer.valueOf(attributes[0]), Double.valueOf(attributes[1]));
            hotelService.addRoom(hotel, newroom);
        }catch (NumberFormatException e) {
            System.out.println("Invalid input for creation. The room was not added.");
        }catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Not enough attributes as input. The room was not added.");
        }catch (TooManyProductsException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addEmployee(Hotel hotel, HotelService hotelService, Scanner scanner) {
        try {
            String[] details = scanner.nextLine().split("/");
            Person person = new Employee(details[0], Integer.valueOf(details[1]), Double.valueOf(details[2]));
            hotelService.addEmployee(hotel, (Employee) person);
        }catch (NumberFormatException e) {
            System.out.println("Invalid input for creation. The employee was not added.");
        }catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Not enough attributes as input. The employee was not added.");
        }catch (TooManyProductsException e) {
            System.out.println(e.getMessage());
        }
    }

    public static  void deleteEmployee(Hotel hotel, HotelService hotelService, Scanner scanner) {
        try {
            String[] details = scanner.nextLine().split("/");
            Integer id = Integer.valueOf(details[0]);
            if(hotel.getEmployees().containsKey(id)) {
                hotel.getEmployees().remove(id);
                System.out.println("The employee has been deleted.");
            }
            else System.out.println("Invalid number id.");
        }catch (NumberFormatException e) {
            System.out.println("Invalid input for delete. The employee was not deleted.");
        }catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Not enough attributes as input. The employee was not deleted.");
        }catch (TooManyProductsException e) {
            System.out.println(e.getMessage());
        }
    }
}
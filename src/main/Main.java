package main;
import model.*;
import service.HotelService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        HotelService hotelService = new HotelService();

        Scanner scanner = new Scanner(System.in);

        Meniu.printDescriere();

        while(true) {
                Meniu.printNextCommand();
                String line = scanner.nextLine();
                switch (line) {
                    case "add booking":
                        System.out.println("What kind of room: single/double ?");
                        String roomType1 = scanner.nextLine();
                        switch (roomType1) {
                            case "single": {
                                System.out.println("Choose the number of wanted room.");
                                hotelService.listAvailableRooms(hotel, "single");
                                int nrwanted = Integer.valueOf(scanner.nextLine());
                                System.out.println("What's the person? name/age/email");
                                String[] details1 = scanner.nextLine().split("/");
                                Person person1 = new Client(details1[0], Integer.valueOf(details1[1]), details1[2]);
                                hotelService.addBooking(hotel, nrwanted, person1);
                                hotelService.addClient(hotel, (Client) person1);
                                break;
                            }
                            case "double": {
                                System.out.println("Choose the number of wanted room.");
                                hotelService.listAvailableRooms(hotel, "double");
                                int nrwanted = Integer.valueOf(scanner.nextLine());
                                System.out.println("What's the first person? name/age/email");
                                String[] details1 = scanner.nextLine().split("/");
                                Person person1 = new Client(details1[0], Integer.valueOf(details1[1]), details1[2]);
                                System.out.println("What's the second person? name/age/email");
                                String[] details2 = scanner.nextLine().split("/");
                                Person person2 = new Client(details2[0], Integer.valueOf(details1[1]), details2[2]);
                                hotelService.addBooking(hotel, nrwanted, person1, person2);
                                hotelService.addClient(hotel, (Client) person1);
                                hotelService.addClient(hotel, (Client) person2);
                                break;
                            }
                            default: System.out.println("This room type doesn't exist.");
                        }
                        break;
                    case "add room":
                        System.out.println("What kind of room: single/double ?");
                        String roomType2 = scanner.nextLine();
                        switch (roomType2) {
                            case "single": {
                                System.out.println("PLease type the room details: number/price");
                                String[] attributes = scanner.nextLine().split("/");
                                Room newroom = new SingleRoom(Integer.valueOf(attributes[0]), Double.valueOf(attributes[1]));
                                hotelService.addRoom(hotel, newroom);
                                break;
                            }
                            case "double": {
                                System.out.println("PLease type the room details: number/price");
                                String[] attributes = scanner.nextLine().split("/");
                                Room newroom = new DoubleRoom(Integer.valueOf(attributes[0]), Double.valueOf(attributes[1]));
                                hotelService.addRoom(hotel, newroom);
                                break;
                            }
                            default : System.out.println("This room type doesn't exist.");
                        }
                        break;
                    case "view free rooms":
                        System.out.println("What kind of room: single/double ?");
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
                    case "view details rooms":
                        hotelService.listRooms(hotel);
                        break;
                    case "view clients":
                        hotelService.listClients(hotel);
                        break;
                    case "add employee":
                        System.out.println("Please insert details of the employee. name/age/salary");
                        String[] details = scanner.nextLine().split("/");
                        Person person = new Employee(details[0], Integer.valueOf(details[1]), Double.valueOf(details[2]));
                        hotelService.addEmployee(hotel, (Employee) person);
                        break;
                    case "view employees":
                        hotelService.listEmployees(hotel);
                        break;
                    case "exit":
                        Meniu.printGreeting();
                        System.exit(0);
                        break;
                    default : System.out.println("This command doesn't exist.");
                }
        }
    }
}
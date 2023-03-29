package main;

public class Meniu {
    public static String descriereShop = "Servus!\nThis is a Hotel.\nHow can we help you?";
    public static String descriereCommands = "Commands:\n" +
            "- add room = add a new room\n" +
            "- view details rooms = view all rooms\n" +
            "- view free rooms = view all available rooms\n" +
            "- add booking = add a booking (set a room as being unavailable)\n" +
            "- view clients = view all clients (people that are having a booking)\n" +
            "- add employee = add a new employee\n" +
            "- view employees = view all employees\n" +
            "- sort employees = sort employees after salary (min->max)\n" +
            "- exit = exiting the program";
    public static String nextCommand = "Please type command: ";
    public static String greeting = "Thank you for coming at our Hotel!\nHope we'll see you soon, kisses!";

    public static void printDescriere() {
        System.out.println(descriereShop);
        System.out.println(descriereCommands);
    }
    public static void printNextCommand() {
        System.out.println(nextCommand);
    }
    public static void printGreeting() {
        System.out.println(greeting);
    }
}

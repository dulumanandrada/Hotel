package main;

public class Meniu {
    public static String descriereShop = "Servus!\nThis is a Hotel.\nHow can we help you?";
    public static String descriereCommands =
            "______________________________________________________________________\n" +
            "|                              COMMANDS                              |\n" +
            "|- add room = add a new room                                         |\n" +
            "|- how many = see how many single and double rooms are               |\n" +
            "|- see rooms = view all rooms                                        |\n" +
            "|- see free rooms = view all available rooms                         |\n" +
            "|- see booked rooms = view all booked rooms                          |\n" +
            "|- sort rooms asc = sort rooms after their price (min->max)          |\n" +
            "|- sort rooms desc = sort rooms after their price (max->min)         |\n" +
            "|- check in = add a booking (set a room as being unavailable)        |\n" +
            "|- check out = delete a booking (set a room as being available again)|\n" +
            "|- see clients = view all clients (people that are having a booking) |\n" +
            "|- add employee = add a new employee                                 |\n" +
            "|- delete employee = delete an employee after the id                 |\n" +
            "|- see employees = view all employees                                |\n" +
            "|- sort employees = sort employees after salary (min->max)           |\n" +
            "|- exit = exiting the program                                        |\n" +
            "|____________________________________________________________________|";
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

package main;

public class Meniu {
    public static String descriereShop = "Servus!\nThis is a Hotel.\nHow can we help you?";
    public static String descriereCommands =
            "______________________________________________________________________\n" +
            "|                              COMMANDS                              |\n" +
            "|- add employee                                                      |\n" +
            "|- update employee                                                   |\n" +
            "|- list employees                                                    |\n" +
            "|- sort employees                                                    |\n" +
            "|- delete employee                                                   |\n" +
            "|- add client                                                        |\n" +
            "|- update client                                                     |\n" +
            "|- list clients                                                      |\n" +
//            "|- delete client                                                     |\n" +
            "|- add room (single/double)                                          |\n" +
            "|- update room price (single/double)                                 |\n" +
            "|- delete room (single/double)                                       |\n" +
            "|- list rooms (single/double)                                        |\n" +
            "|- sort rooms (asc/desc)                                             |\n" +
            "|- how many = how many single rooms and double rooms                 |\n" +
            "|- list all rooms (a/u) = available or unavailable                   |\n"+
            "|- check in                                                          |\n"+
            "|- check out                                                         |\n"+
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

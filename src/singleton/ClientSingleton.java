package singleton;

import model.Client;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ClientSingleton {
    private static ClientSingleton single_instance = null;
    private List<Client> clients = new ArrayList<>();
    public static ClientSingleton getInstance() {
        if(single_instance == null)
            single_instance = new ClientSingleton();
        return single_instance;
    }
    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
    public List<Client> getClients() {
        return clients;
    }
    private static List<String[]> getCSVColumns(String fileName) {
        List<String[]> columns = new ArrayList<>();
        try(var in = new BufferedReader(new FileReader(fileName))) {
            String line;
            while((line = in.readLine()) != null) {
                String[] fields = line.replaceAll(" ", "").split(",");
                columns.add(fields);
            }
        }catch (IOException e) {
            System.out.println("No saved clients!");
        }
        return columns;
    }
    public void loadFromCSV() {
        try{
            var columns = ClientSingleton.getCSVColumns("/Users/andradaduluman/IdeaProjects/Hotel/src/data/client.csv");
            for(var fields : columns) {
                var newClient = new Client(fields[0], Integer.parseInt(fields[1]), fields[2]);
                clients.add(newClient);
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void dumpToCSV() {
        try{
            var writer = new FileWriter("/Users/andradaduluman/IdeaProjects/Hotel/src/data/client.csv");
            for(var client : clients){
                writer.write(client.toCSV());
                writer.write("\n");
            }
            writer.close();
        }catch (IOException e){
            System.out.println(e.toString());
        }
    }
}

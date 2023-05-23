package singleton;

import model.Client;
import model.SingleRoom;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SingleRoomSingleton {
    private static SingleRoomSingleton single_instance = null;
    private List<SingleRoom> singleRooms = new ArrayList<>();
    public static SingleRoomSingleton getInstance() {
        if(single_instance == null)
            single_instance = new SingleRoomSingleton();
        return single_instance;
    }
    public void setSingleRooms(List<SingleRoom> singleRooms) {
        this.singleRooms = singleRooms;
    }
    public List<SingleRoom> getSingleRooms() {
        return singleRooms;
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
            System.out.println("No saved singleRooms!");
        }
        return columns;
    }
    public void loadFromCSV() {
        try{
            var columns = SingleRoomSingleton.getCSVColumns("/Users/andradaduluman/IdeaProjects/Hotel/src/data/singleroom.csv");
            for(var fields : columns) {
                var newClient = new Client(fields[3], Integer.parseInt(fields[4]), fields[5]);
                var newSingleRoom = new SingleRoom(Integer.parseInt(fields[0]), Boolean.parseBoolean(fields[1]), Double.parseDouble(fields[2]), newClient);
                singleRooms.add(newSingleRoom);
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void dumpToCSV() {
        try{
            var writer = new FileWriter("/Users/andradaduluman/IdeaProjects/Hotel/src/data/singleroom.csv");
            for(var singleRoom : singleRooms){
                writer.write(singleRoom.toCSV());
                writer.write("\n");
            }
            writer.close();
        }catch (IOException e){
            System.out.println(e.toString());
        }
    }
}

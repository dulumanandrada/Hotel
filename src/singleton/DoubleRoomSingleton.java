package singleton;

import model.Client;
import model.DoubleRoom;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class DoubleRoomSingleton {
    private static DoubleRoomSingleton single_instance = null;
    private List<DoubleRoom> doubleRooms = new ArrayList<>();
    public static DoubleRoomSingleton getInstance() {
        if(single_instance == null)
            single_instance = new DoubleRoomSingleton();
        return single_instance;
    }
    public void setDoubleRooms(List<DoubleRoom> doubleRooms) {
        this.doubleRooms = doubleRooms;
    }
    public List<DoubleRoom> getDoubleRooms() {
        return doubleRooms;
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
            System.out.println("No saved doubleRooms!");
        }
        return columns;
    }
    public void loadFromCSV() {
        try{
            var columns = DoubleRoomSingleton.getCSVColumns("/Users/andradaduluman/IdeaProjects/Hotel/src/data/doubleroom.csv");
            for(var fields : columns) {
                var newFirstClient = new Client(fields[3], Integer.parseInt(fields[4]), fields[5]);
                var newSecondClient = new Client(fields[6], Integer.parseInt(fields[7]), fields[8]);
                var newDoubleRoom = new DoubleRoom(Integer.parseInt(fields[0]), Boolean.parseBoolean(fields[1]), Double.parseDouble(fields[2]), newFirstClient, newSecondClient);
                doubleRooms.add(newDoubleRoom);
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void dumpToCSV() {
        try{
            var writer = new FileWriter("/Users/andradaduluman/IdeaProjects/Hotel/src/data/doubleroom.csv");
            for(var doubleRoom : doubleRooms){
                writer.write(doubleRoom.toCSV());
                writer.write("\n");
            }
            writer.close();
        }catch (IOException e){
            System.out.println(e.toString());
        }
    }
}

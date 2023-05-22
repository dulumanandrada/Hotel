package model;

public abstract class Room {
    private long Id;
    private int Number;
    private Boolean Availability = true;
    private double Price;

    public Room() { }
    public Room(int number, double price) {
        this.Number = number;
        this.Price = price;
    }
    public Room(long id, int number, boolean availability, double price) {
        this.Id = id;
        this.Number = number;
        this.Availability = availability;
        this.Price = price;
    }

    public int getNumber() {
        return Number;
    }
    public void setNumber(int number) {
        Number = number;
    }
    public Boolean getAvailability() {
        return Availability;
    }
    public void setAvailability(Boolean availability) {
        Availability = availability;
    }
    public double getPrice() {
        return Price;
    }
    public void setPrice(double price) {
        Price = price;
    }

    @Override
    public String toString() {
        return "###### Room Number: " + this.Number + " ######\n- availability: " + this.Availability + "\n- price: " + this.Price + "\n";
    }
}

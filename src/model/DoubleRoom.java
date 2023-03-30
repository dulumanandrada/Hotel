package model;

public class DoubleRoom extends SingleRoom {
    private Person person2 = new Client();

    public DoubleRoom() {
        super();
    }
    public DoubleRoom(int number, double price) {
        super(number, price);
    }

    public void setPerson2(Person person2) {
        this.person2 = person2;
    }

    @Override
    public String toString() {
        return super.toString() + "second person: " + person2.toString();
    }
}

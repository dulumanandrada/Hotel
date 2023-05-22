package model;

public class DoubleRoom extends SingleRoom {
    private long Id;
    private Person secondPerson = new Client();

    public DoubleRoom() {
        super();
    }
    public DoubleRoom(int number, double price) {
        super(number, price);
    }
    public DoubleRoom(long id, int number, boolean availability, double price, Person firstPerson, Person secondPerson) {
        super(id, number, availability, price, firstPerson);
        this.secondPerson = secondPerson;
    }
    public void setSecondPerson(Person secondPerson) {
        this.secondPerson = secondPerson;
    }
    public Person getSecondPerson() {
        return secondPerson;
    }
    @Override
    public String toString() {
        return super.toString() + "second person: " + secondPerson.toString();
    }
}

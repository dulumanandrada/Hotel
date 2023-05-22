package model;

public class SingleRoom extends Room {
    private long Id;
    private Person firstPerson = new Client();

    public SingleRoom() {
        super();
    }
    public SingleRoom(int number, double price) {
        super(number, price);
    }
    public SingleRoom(long id, int number, boolean availability, double price, Person person) {
        super(id, number, availability, price);
        this.firstPerson = person;
    }
    public SingleRoom(int number, boolean availability, double price, Person person) {
        super(number, availability, price);
        this.firstPerson = person;
    }
    public Person getFirstPerson() {
        return firstPerson;
    }
    public void setFirstPerson(Person firstPerson) {
        this.firstPerson = firstPerson;
    }

    @Override
    public String toString() {
        return super.toString() + "first person: " + firstPerson.toString() ;
    }
    public String toCSV() {
        return super.toCSV() + "," + firstPerson.toCSV();
    }
}

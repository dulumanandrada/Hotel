package model;

public class SingleRoom extends Room {
    private Person person1 = new Client();

    public SingleRoom() {
        super();
    }
    public SingleRoom(int number, double price) {
        super(number, price);
    }
    public Person getPerson1() {
        return person1;
    }
    public void setPerson1(Person person1) {
        this.person1 = person1;
    }

    @Override
    public String toString() {
        return super.toString() + "first person: " + person1.toString() ;
    }
}

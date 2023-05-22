package model;

public class Client extends Person {
    private long Id;
    private String Email;

    public Client() {
        super();
    }
    public Client(long id, String name, int age, String email) {
        super(id, name, age);
        this.Email = email;
    }
    public Client(String name, int age, String email) {
        super(name, age);
        this.Email = email;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    @Override
    public String toString() {
        return super.toString() + " , email: " + this.Email + "\n";
    }
    public String toCSV() {
        return super.toCSV() + "," + this.Email;
    }
}

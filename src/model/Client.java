package model;

public class Client extends Person {
    private int Id;
    private String Email;

    public Client() {
        super();
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
}

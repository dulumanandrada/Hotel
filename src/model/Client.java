package model;

public class Client extends Person {
    private String Email;

    public Client() {
        super();
    }
    public Client(String name, int age, String email) {
        super(name, age);
        this.Email = email;
    }
    public String getEmail() {
        return this.Email;
    }
    public void setEmail(String email) {
        this.Email = email;
    }

    @Override
    public String toString() {
        return super.toString() + " , email: " + this.Email + " ";
    }
}

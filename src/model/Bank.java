package model;

public class Bank {
    private long id;
    private String name;
    private int age;

    public Bank() {}
    public Bank(String name, int age){
        this.name = name;
        this.age = age;
    }

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
}

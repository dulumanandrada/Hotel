package model;
public abstract class Person {
    private long Id;
    private String Name;
    private int Age;

    public Person() { }
    public Person(long id, String name, int age) {
        this.Id = id;
        this.Name = name;
        this.Age = age;
    }
    public Person(String name, int age) {
        this.Name = name;
        this.Age = age;
    }

    public long getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public void setAge(int age) {
        Age = age;
    }

    public int getAge() {
        return Age;
    }

    @Override
    public String toString() {
        return "Person ID: " + this.Id + " name: " + this.Name + " , age: " + this.Age + " ";
    }
    public String toCSV() {
        return this.Name + "," + this.Age;
    }
}

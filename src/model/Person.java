package model;
public abstract class Person {
    private int Id;
    private String Name;
    private int Age;

    public Person() { }
    public Person(String name, int age) {
        this.Name = name;
        this.Age = age;
    }

    public int getId() {
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
        return "Person name: " + this.Name + " , age: " + this.Age + " ";
    }
}

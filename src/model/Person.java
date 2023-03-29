package model;
public abstract class Person {
    private int Id;
    private String Name;
    private int Age;

    public Person() { }
    public Person(int id, String name, int age) {
        this.Id = id;
        this.Name = name;
        this.Age = age;
    }
    //get-eri
    public int getId() {
        return Id;
    }
    public String getName() {
        return Name;
    }
    public int getAge() {
        return Age;
    }

    //set-eri
    public void setId(int id) {
        Id = id;
    }
    public void setName(String name) {
        Name = name;
    }
    public void setAge(int age) {
        Age = age;
    }

    @Override
    public String toString() {
        return "Id: " + this.Id + " , name: " + this.Name + " , age: " + this.Age;
    }
}

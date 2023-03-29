package model;

public class Employee extends Person {
    private double Salary;

    public Employee() {
        super();
    }
    public Employee(int id, String name, int age, double salary) {
        super(id, name, age);
        this.Salary = salary;
    }
    public void setSalary(double salary) {
        this.Salary = salary;
    }
    public double getSalary() {
        return this.Salary;
    }

    @Override
    public String toString() {
        return super.toString() + " , salary: " + this.Salary;
    }
}

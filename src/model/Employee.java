package model;

public class Employee extends Person {
    private double Salary;

    public Employee() {
        super();
    }
    public Employee(String name, int age, double salary) {
        super(name, age);
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
        return super.toString() + " , salary: " + this.Salary + " ";
    }
}

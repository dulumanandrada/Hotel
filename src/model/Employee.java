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
        return super.toString() + " , salary: " + this.Salary + "\n";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + getName().hashCode();
        result = prime * result + getAge();
        result = prime * result + (int)getSalary();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Employee other = (Employee) obj;
        if (getName() != other.getName())
            return false;
        if (getAge() != other.getAge())
            return false;
        if (getSalary() != other.getSalary())
            return false;
        return true;
    }
}

package model;

import java.util.Comparator;

public class EmployeeComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        if(p1 instanceof Employee && p2 instanceof Employee) {
            Employee e1 = (Employee) p1;
            Employee e2 = (Employee) p2;
            return (int) (e1.getSalary() - e2.getSalary());
        }
        return 0;
    }
}

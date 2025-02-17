package model;

import java.util.Comparator;

public class EmployeeComparators {
    
    public static final Comparator<Employee> BY_NAME = Comparator.comparing(Employee::getName);

    public static final Comparator<Employee> BY_AGE = Comparator.comparingInt(Employee::getAge);

    public static final Comparator<Employee> BY_DOB = Comparator.comparing(Employee::getDob);
}

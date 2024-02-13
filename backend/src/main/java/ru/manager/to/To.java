package ru.manager.to;

import ru.manager.model.Employee;

public class To {
    public static Employee get(EmployeeTo emp) {
        return new Employee(emp.getId(), emp.getName(), emp.getNote());
    }
}

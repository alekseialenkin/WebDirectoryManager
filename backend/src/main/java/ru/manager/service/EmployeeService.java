package ru.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.manager.model.Department;
import ru.manager.model.Employee;
import ru.manager.model.Profession;
import ru.manager.repository.DepartmentRepository;
import ru.manager.repository.EmployeeRepository;
import ru.manager.repository.ProfessionRepository;
import ru.manager.to.EmployeeTo;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ProfessionRepository professionRepository;

    public Employee save(Employee employee, String profession, String department) {
        Profession prof = professionRepository.getByNameExists(profession);
        Department dep = departmentRepository.getByNameExists(department);
        employee.setDepartment(dep);
        employee.setProfession(prof);
        return employeeRepository.save(employee);
    }

    public EmployeeTo get(int id) {
        Employee employee = employeeRepository.getExisted(id);
        return new EmployeeTo(employee.getName(), employee.getNote(),
                employee.id(), employee.getProfession().getName(),
                employee.getDepartment().getName());
    }

    public List<EmployeeTo> getAll() {
        List<Employee> list = employeeRepository.findAll();
        return list.stream().map(employee -> new EmployeeTo(employee.getName(),
                employee.getNote(), employee.id(), employee.getProfession().getName(),
                employee.getDepartment().getName())).toList();
    }
}

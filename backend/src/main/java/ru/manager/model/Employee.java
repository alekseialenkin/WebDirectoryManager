package ru.manager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee extends NamedNoteEntity {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "proffession_id")
    @JsonIgnore
    private Profession profession;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    @JsonIgnore
    private Department department;

    public Employee() {
    }

    public Employee(Integer id, String name, String note) {
        super(id, name, note);
    }

    public Employee(Integer id, String name, String note, Profession profession, Department department) {
        super(id, name, note);
        this.profession = profession;
        this.department = department;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "profession=" + profession +
                ", department=" + department +
                '}';
    }
}

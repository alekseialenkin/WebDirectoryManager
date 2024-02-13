package ru.manager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "professions", uniqueConstraints = @UniqueConstraint(columnNames = "name", name = "profession_unique_name_idx"))
public class Profession extends NamedNoteEntity {
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "profession")
    @JsonIgnore
    private List<Employee> employees;

    public Profession() {
    }

    public Profession(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Profession{" +
                "name='" + name + '\'' +
                ", note='" + note + '\'' +
                ", id=" + id +
                '}';
    }
}

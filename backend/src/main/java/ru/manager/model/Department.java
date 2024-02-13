package ru.manager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Department extends NamedNoteEntity {

    public Department() {
    }

    public Department(Integer id, String name, String note) {
        super(id, name, note);
    }

}
